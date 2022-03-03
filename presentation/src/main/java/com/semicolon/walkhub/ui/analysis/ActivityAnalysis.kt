package com.semicolon.walkhub.ui.analysis

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.semicolon.domain.entity.exercise.DailyExerciseEntity
import com.semicolon.domain.entity.exercise.ExerciseAnalysisResultEntity
import com.semicolon.domain.entity.level.LevelEntity
import com.semicolon.walkhub.ui.base.Body2
import com.semicolon.walkhub.ui.base.Body3
import com.semicolon.walkhub.ui.base.Subtitle4
import com.semicolon.walkhub.ui.base.gray800
import com.semicolon.walkhub.viewmodel.analysis.ActivityAnalysisViewModel
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneId

@Composable
fun ActivityAnalysis(onBackButtonClick: () -> Unit) {
    val viewModel: ActivityAnalysisViewModel = hiltViewModel()
    viewModel.fetchDailyExerciseRecordUseCase()
    viewModel.fetchExerciseAnalysisResult()
    viewModel.fetchLevelList()
    ActivityAnalysis(onBackButtonClick, viewModel)
}

@Composable
fun ActivityAnalysis(onBackButtonClick: () -> Unit, viewModel: ActivityAnalysisViewModel) {
    val scrollState = rememberScrollState()
    var graphState by remember { mutableStateOf(GraphType.WEEKLY) }
    var analysisResult by remember {
        mutableStateOf(
            ExerciseAnalysisResultEntity(
                0,
                listOf(1..28).flatten(),
                0,
                0,
                0,
                0f,
                0
            )
        )
    }
    var dailyExercise by remember {
        mutableStateOf(
            DailyExerciseEntity(
                0, 0, 0, 0f
            )
        )
    }
    var cardList by remember { mutableStateOf(listOf<LevelEntity>()) }
    var card by remember {
        mutableStateOf(
            LevelEntity(
                0, "", "", 0, "", 0, ""
            )
        )
    }
    card = if (cardList.isNotEmpty())
        cardList.last { it.calories <= dailyExercise.burnedKilocalories } else card
    val composableScope = rememberCoroutineScope()
    LaunchedEffect(analysisResult, dailyExercise) {
        composableScope.launch {
            viewModel.eventFlow.collect {
                if (it is ActivityAnalysisViewModel.Event.AnalysisResult)
                    analysisResult = it.result
                if (it is ActivityAnalysisViewModel.Event.ExerciseRecord)
                    dailyExercise = it.record
                if (it is ActivityAnalysisViewModel.Event.Level)
                    cardList = it.level
            }
        }
    }

    Box(
        Modifier
            .background(Color(0xFFF9F9F9))
            .verticalScroll(scrollState)
    ) {
        Column(
            Modifier
                .padding(top = 260.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.White, RoundedCornerShape(topStart = 36.dp, topEnd = 36.dp))
                .padding(top = 120.dp)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 40.dp)
            ) {
                StepsAndCalories(dailyExercise, analysisResult)
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentAlignment = Alignment.TopCenter
            ) {
                DistanceAndTime(dailyExercise)
            }
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GraphToggleButton {
                    graphState =
                        if (graphState == GraphType.WEEKLY) GraphType.MONTHLY else GraphType.WEEKLY
                }
                Spacer(modifier = Modifier.height(8.dp))
                WalkGraph(graphState, analysisResult.walkCountList)
            }
            Column(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 28.dp)
                    .padding(horizontal = 40.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                WalkCount(graphState, analysisResult)
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppBar(text = "활동분석") {
                onBackButtonClick()
            }
            Spacer(modifier = Modifier.size(12.dp))
            LevelCard(card)
        }
    }
}

@Composable
fun LevelCard(card: LevelEntity) {
    CaloriesLevelCard(
        imageUrl = card.foodImageUrl,
        name = card.foodName,
        calories = card.calories,
        size = card.size,
        level = card.level,
        progress = 0.5f,
        message = card.message
    )
}

@Composable
fun WalkCount(graphType: GraphType, analysisResult: ExerciseAnalysisResultEntity) {
    val items =
        if (graphType == GraphType.WEEKLY) analysisResult.walkCountList.takeLast(7)
        else analysisResult.walkCountList
    val total = items.sumOf { it }.toString()
    val avg = items.average().toInt().toString()
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Body2(text = "걸음수 총합", color = gray800)
        Subtitle4(text = total, color = gray800)
    }
    Spacer(modifier = Modifier.height(12.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Body2(text = "평균 걸음수", color = gray800)
        Subtitle4(text = avg, color = gray800)
    }
}

@Composable
fun StepsAndCalories(
    dailyExercise: DailyExerciseEntity,
    analysisResult: ExerciseAnalysisResultEntity
) {
    val date = LocalDate.now(ZoneId.systemDefault())
    val goal = analysisResult.dailyWalkCountGoal
    val steps = dailyExercise.stepCount
    val progress = if (goal == 0) 0f else steps.toFloat() / goal
    Body2(
        text = "${date.month.value}월 ${date.dayOfMonth}일 (${date.dayOfWeek.toKoreanString()})",
        color = Color(0xFF8E8E8E)
    )
    Spacer(modifier = Modifier.size(12.dp))
    Body2(text = "걸음 수", color = gray800)
    Row(
        verticalAlignment = Alignment.Bottom
    ) {
        Subtitle4(text = steps.toString())
        Spacer(modifier = Modifier.size(4.dp))
        Body2(
            text = "/$goal 걸음", color = gray800,
            modifier = Modifier.padding(bottom = 1.dp)
        )
    }
    Spacer(modifier = Modifier.size(4.dp))
    ProgressBar(
        percent = progress,
        backgroundColor = Color(0xFFE5E5E5),
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.size(12.dp))
    Body2(text = "칼로리 소모", color = gray800)
    Body3(text = "건강정보를 기준으로 측정했어요", color = gray800)
    Row(
        verticalAlignment = Alignment.Bottom
    ) {
        Subtitle4(text = String.format("%.2f", dailyExercise.burnedKilocalories))
        Spacer(modifier = Modifier.size(4.dp))
        Body2(
            text = "Kcal", color = gray800,
            modifier = Modifier.padding(bottom = 1.dp)
        )
    }
}

@Composable
fun DistanceAndTime(exerciseEntity: DailyExerciseEntity) {
    val distance = String.format("%.1f", (exerciseEntity.traveledDistanceAsMeter.toFloat() / 1000))
    val hour = (exerciseEntity.exerciseTimeAsMilli / 1000 / 60 / 60).toString()
    val minute = String.format("%02d", (exerciseEntity.exerciseTimeAsMilli / 1000 / 60) % 60)
    Spacer(
        modifier = Modifier
            .padding(top = 38.dp, bottom = 30.dp)
            .height(28.dp)
            .width(1.dp)
            .background(Color(0xFFE3E3E3))
    )
    Row(
        modifier = Modifier
            .padding(top = 22.dp)
            .wrapContentSize(),
        verticalAlignment = Alignment.Bottom
    ) {
        Body2(text = "거리", color = gray800)
        Spacer(modifier = Modifier.width(138.dp))
        Body2(text = "시간", color = gray800)
    }
    Row(
        modifier = Modifier
            .padding(top = 46.dp)
            .wrapContentSize(),
        verticalAlignment = Alignment.Bottom
    ) {
        Subtitle4(text = distance, color = gray800)
        Spacer(modifier = Modifier.width(4.dp))
        Body2(
            text = "km", color = gray800,
            modifier = Modifier.padding(bottom = 1.dp)
        )
        Spacer(modifier = Modifier.width(105.dp))
        Subtitle4(text = hour, color = gray800)
        Spacer(modifier = Modifier.width(2.dp))
        Body2(
            text = "h", color = gray800,
            modifier = Modifier.padding(bottom = 1.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Subtitle4(text = minute, color = gray800)
        Spacer(modifier = Modifier.width(2.dp))
        Body2(
            text = "m", color = gray800,
            modifier = Modifier.padding(bottom = 1.dp)
        )
    }
}

@Composable
fun WalkGraph(graphType: GraphType, items: List<Int>) {
    WalkGraph(items = items.toGraphItemList(), type = graphType)
}

@Preview(showBackground = true)
@Composable
fun PreviewActivityAnalysis() {
    ActivityAnalysis {}
}