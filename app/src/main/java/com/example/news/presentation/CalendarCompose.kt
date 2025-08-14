package com.example.news.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MonthlyCalendar(
    modifier: Modifier = Modifier,
    selectedDates: Set<LocalDate> = emptySet(),
    onDateSelected: (LocalDate) -> Unit = {},
    currentDate: LocalDate = LocalDate.now()
) {
    var currentMonth by remember { mutableStateOf(YearMonth.now()) }
    val today = LocalDate.now()
    
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Заголовок с навигацией по месяцам
        CalendarHeader(
            currentMonth = currentMonth,
            onPreviousMonth = { currentMonth = currentMonth.minusMonths(1) },
            onNextMonth = { currentMonth = currentMonth.plusMonths(1) }
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Дни недели
        WeekDaysHeader()
        
        Spacer(modifier = Modifier.height(8.dp))
        
        // Сетка календаря
        CalendarGrid(
            currentMonth = currentMonth,
            selectedDates = selectedDates,
            onDateSelected = onDateSelected,
            today = today,
            currentDate = currentDate
        )
    }
}

@Composable
private fun CalendarHeader(
    currentMonth: YearMonth,
    onPreviousMonth: () -> Unit,
    onNextMonth: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onPreviousMonth) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "Предыдущий месяц"
            )
        }
        
        Text(
            text = currentMonth.format(DateTimeFormatter.ofPattern("LLLL yyyy", Locale("ru"))),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        
        IconButton(onClick = onNextMonth) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Следующий месяц"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalendarScreenPreview() {
    MaterialTheme {
        CalendarScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun MonthlyCalendarPreview() {
    MaterialTheme {
        MonthlyCalendar(
            selectedDates = setOf(
                LocalDate.now().plusDays(3),
                LocalDate.now().plusDays(7),
                LocalDate.now().plusDays(15)
            ),
            currentDate = LocalDate.now().plusDays(10)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CalendarHeaderPreview() {
    MaterialTheme {
        CalendarHeader(
            currentMonth = YearMonth.now(),
            onPreviousMonth = {},
            onNextMonth = {}
        )
    }
}

@Composable
private fun WeekDaysHeader() {
    val weekDays = listOf("Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс")
    
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        weekDays.forEach { day ->
            Text(
                text = day,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun CalendarGrid(
    currentMonth: YearMonth,
    selectedDates: Set<LocalDate>,
    onDateSelected: (LocalDate) -> Unit,
    today: LocalDate,
    currentDate: LocalDate
) {
    val firstDayOfMonth = currentMonth.atDay(1)
    val lastDayOfMonth = currentMonth.atEndOfMonth()
    val firstDayOfWeek = firstDayOfMonth.dayOfWeek.value % 7 // Понедельник = 0
    
    // Создаем список всех дней для отображения
    val daysToShow = mutableListOf<LocalDate?>()
    
    // Добавляем пустые ячейки для дней предыдущего месяца
    repeat(firstDayOfWeek) {
        daysToShow.add(null)
    }
    
    // Добавляем все дни текущего месяца
    for (day in 1..lastDayOfMonth.dayOfMonth) {
        daysToShow.add(currentMonth.atDay(day))
    }
    
    // Заполняем оставшиеся ячейки до полных недель
    while (daysToShow.size % 7 != 0) {
        daysToShow.add(null)
    }
    
    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(daysToShow) { date ->
            CalendarDay(
                date = date,
                isSelected = date != null && selectedDates.contains(date),
                isToday = date == today,
                isCurrentDate = date == currentDate,
                onDateSelected = { date?.let(onDateSelected) }
            )
        }
    }
}

@Composable
private fun CalendarDay(
    date: LocalDate?,
    isSelected: Boolean,
    isToday: Boolean,
    isCurrentDate: Boolean,
    onDateSelected: () -> Unit
) {
    val backgroundColor = when {
        isCurrentDate -> MaterialTheme.colorScheme.primary
        isSelected -> MaterialTheme.colorScheme.primaryContainer
        isToday -> MaterialTheme.colorScheme.secondaryContainer
        else -> Color.Transparent
    }
    
    val textColor = when {
        isCurrentDate ->  Color.Green//MaterialTheme.colorScheme.onPrimary
        isSelected -> MaterialTheme.colorScheme.onPrimaryContainer
        isToday ->  Color.Red//MaterialTheme.colorScheme.onSecondaryContainer
        date == null -> Color.Transparent
        else -> MaterialTheme.colorScheme.onSurface
    }
    
    val borderColor = when {
        isToday && !isCurrentDate -> MaterialTheme.colorScheme.secondary
        else -> Color.Transparent
    }
    
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(backgroundColor)
            .border(
                width = if (borderColor == Color.Transparent) 0.dp else 3.dp,
                color = borderColor,
                shape = CircleShape
            )
            .clickable(enabled = date != null) { onDateSelected() },
        contentAlignment = Alignment.Center
    ) {
        if (date != null) {
            Text(
                text = date.dayOfMonth.toString(),
                color = textColor,
                fontSize = 14.sp,
                fontWeight = if (isCurrentDate || isToday) FontWeight.Bold else FontWeight.Normal
            )
        }
    }
}

// Пример использования календаря
@Composable
fun CalendarScreen() {
    var selectedDates by remember { mutableStateOf(setOf<LocalDate>()) }
    val currentDate = LocalDate.now()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Календарь",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        MonthlyCalendar(
            selectedDates = selectedDates,
            currentDate = currentDate,
            onDateSelected = { date ->
                selectedDates = if (selectedDates.contains(date)) {
                    selectedDates - date
                } else {
                    selectedDates + date
                }
            }
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Показываем выбранные даты
        if (selectedDates.isNotEmpty()) {
            Text(
                text = "Выбранные даты:",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            selectedDates.sorted().forEach { date ->
                Text(
                    text = date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale("ru"))),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
            }
        }
    }
}
