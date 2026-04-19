package com.kharrat.blooddonationapp.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.VolunteerActivism
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlin.math.min

private interface TreeGrowthState {
    val stageIndex: Int
    val label: String
    val progress: Float
    fun onFabClick(machine: TreeGrowthStateMachine)
}

private class PlantedState : TreeGrowthState {
    override val stageIndex: Int = 1
    override val label: String = "Stage 1/4 - Planted"
    override val progress: Float = 0.25f

    override fun onFabClick(machine: TreeGrowthStateMachine) {
        machine.state = SaplingState()
    }
}

private class SaplingState : TreeGrowthState {
    override val stageIndex: Int = 2
    override val label: String = "Stage 2/4 - Sapling"
    override val progress: Float = 0.5f

    override fun onFabClick(machine: TreeGrowthStateMachine) {
        machine.state = YoungTreeState()
    }
}

private class YoungTreeState : TreeGrowthState {
    override val stageIndex: Int = 3
    override val label: String = "Stage 3/4 - Young Tree"
    override val progress: Float = 0.75f

    override fun onFabClick(machine: TreeGrowthStateMachine) {
        machine.state = FullTreeState()
    }
}

private class FullTreeState : TreeGrowthState {
    override val stageIndex: Int = 4
    override val label: String = "Stage 4/4 - Full Grown"
    override val progress: Float = 1f

    override fun onFabClick(machine: TreeGrowthStateMachine) {
        machine.treeCount += 1
        machine.state = PlantedState()
    }
}

private class TreeGrowthStateMachine(
    initialTreeCount: Int = 1,
    initialState: TreeGrowthState = PlantedState()
) {
    var treeCount by mutableStateOf(initialTreeCount)
    var state by mutableStateOf(initialState)

    fun advance() {
        state.onFabClick(this)
    }
}

@Composable
fun GardenScreen(modifier: Modifier = Modifier) {
    val machine = remember { TreeGrowthStateMachine() }

    Box(
        modifier = modifier
            .fillMaxSize()

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 14.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Rounded.VolunteerActivism,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "My Garden",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp)) {
                    Text(
                        text = "Tree #${machine.treeCount}",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = machine.state.label,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75f),
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            LowPolyTreeCanvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                state = machine.state
            )
        }

        FloatingActionButton(
            onClick = { machine.advance() },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 22.dp, bottom = 28.dp),
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            shape = CircleShape
        ) {
            Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = "Advance tree growth"
            )
        }
    }
}

@Composable
private fun LowPolyTreeCanvas(
    state: TreeGrowthState,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = androidx.compose.foundation.shape.RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF7FBF4)),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            drawGround()
            when (state.stageIndex) {
                1 -> drawStagePlanted()
                2 -> drawStageSapling()
                3 -> drawStageYoungTree()
                else -> drawStageFullTree()
            }
            drawProgressPips(state.progress)
        }
    }
}

private fun DrawScope.drawGround() {
    val groundTop = size.height * 0.72f
    val ground = Path().apply {
        moveTo(0f, groundTop)
        lineTo(size.width * 0.22f, groundTop - 20f)
        lineTo(size.width * 0.58f, groundTop + 12f)
        lineTo(size.width, groundTop - 10f)
        lineTo(size.width, size.height)
        lineTo(0f, size.height)
        close()
    }

    drawPath(path = ground, color = Color(0xFFCDE5B7))
    drawRect(
        color = Color(0xFFDCEFCC),
        topLeft = Offset(0f, groundTop + 6f),
        size = Size(size.width, size.height - groundTop)
    )
}

private fun DrawScope.drawStagePlanted() {
    val centerX = size.width * 0.5f
    val baseY = size.height * 0.72f

    val soil = Path().apply {
        moveTo(centerX - 52f, baseY + 8f)
        lineTo(centerX + 48f, baseY + 12f)
        lineTo(centerX + 32f, baseY + 34f)
        lineTo(centerX - 58f, baseY + 30f)
        close()
    }
    drawPath(soil, Color(0xFF8D623A))

    val sprout = Path().apply {
        moveTo(centerX, baseY + 6f)
        lineTo(centerX + 8f, baseY - 18f)
        lineTo(centerX + 16f, baseY + 6f)
        close()
    }
    drawPath(sprout, Color(0xFF63A85E))

    val sprout2 = Path().apply {
        moveTo(centerX - 2f, baseY + 4f)
        lineTo(centerX - 10f, baseY - 16f)
        lineTo(centerX - 20f, baseY + 4f)
        close()
    }
    drawPath(sprout2, Color(0xFF6CB668))
}

private fun DrawScope.drawStageSapling() {
    val centerX = size.width * 0.5f
    val baseY = size.height * 0.72f

    drawRect(
        color = Color(0xFF8A5A33),
        topLeft = Offset(centerX - 9f, baseY - 68f),
        size = Size(18f, 74f)
    )

    val crown1 = Path().apply {
        moveTo(centerX, baseY - 130f)
        lineTo(centerX - 40f, baseY - 66f)
        lineTo(centerX + 40f, baseY - 66f)
        close()
    }
    drawPath(crown1, Color(0xFF5EAB58))

    val crown2 = Path().apply {
        moveTo(centerX + 5f, baseY - 114f)
        lineTo(centerX - 6f, baseY - 88f)
        lineTo(centerX + 34f, baseY - 82f)
        close()
    }
    drawPath(crown2, Color(0xFF7BC273))
}

private fun DrawScope.drawStageYoungTree() {
    val centerX = size.width * 0.5f
    val baseY = size.height * 0.72f

    drawRect(
        color = Color(0xFF7A4D2D),
        topLeft = Offset(centerX - 14f, baseY - 112f),
        size = Size(28f, 118f)
    )

    val crownA = Path().apply {
        moveTo(centerX, baseY - 210f)
        lineTo(centerX - 72f, baseY - 112f)
        lineTo(centerX + 66f, baseY - 106f)
        close()
    }
    drawPath(crownA, Color(0xFF4F9C4A))

    val crownB = Path().apply {
        moveTo(centerX - 42f, baseY - 158f)
        lineTo(centerX - 94f, baseY - 98f)
        lineTo(centerX - 22f, baseY - 94f)
        close()
    }
    drawPath(crownB, Color(0xFF67AF63))

    val crownC = Path().apply {
        moveTo(centerX + 50f, baseY - 164f)
        lineTo(centerX + 10f, baseY - 104f)
        lineTo(centerX + 96f, baseY - 94f)
        close()
    }
    drawPath(crownC, Color(0xFF79BF74))
}

private fun DrawScope.drawStageFullTree() {
    val centerX = size.width * 0.5f
    val baseY = size.height * 0.72f
    val scale = min(size.width / 360f, size.height / 520f)

    drawRect(
        color = Color(0xFF6E4529),
        topLeft = Offset(centerX - 18f * scale, baseY - 154f * scale),
        size = Size(36f * scale, 160f * scale)
    )

    val poly1 = Path().apply {
        moveTo(centerX, baseY - 290f * scale)
        lineTo(centerX - 108f * scale, baseY - 154f * scale)
        lineTo(centerX + 102f * scale, baseY - 146f * scale)
        close()
    }
    drawPath(poly1, Color(0xFF418C3E))

    val poly2 = Path().apply {
        moveTo(centerX - 84f * scale, baseY - 210f * scale)
        lineTo(centerX - 150f * scale, baseY - 126f * scale)
        lineTo(centerX - 48f * scale, baseY - 116f * scale)
        close()
    }
    drawPath(poly2, Color(0xFF5BA757))

    val poly3 = Path().apply {
        moveTo(centerX + 96f * scale, baseY - 214f * scale)
        lineTo(centerX + 34f * scale, baseY - 120f * scale)
        lineTo(centerX + 160f * scale, baseY - 112f * scale)
        close()
    }
    drawPath(poly3, Color(0xFF6DB969))

    val poly4 = Path().apply {
        moveTo(centerX + 6f * scale, baseY - 248f * scale)
        lineTo(centerX - 24f * scale, baseY - 198f * scale)
        lineTo(centerX + 58f * scale, baseY - 192f * scale)
        close()
    }
    drawPath(poly4, Color(0xFF7FC77B))
}

private fun DrawScope.drawProgressPips(progress: Float) {
    val totalPips = 4
    val active = (progress * totalPips).toInt().coerceIn(0, totalPips)
    val startX = size.width * 0.5f - 40f
    val y = size.height * 0.12f

    repeat(totalPips) { index ->
        drawCircle(
            color = if (index < active) Color(0xFF2E7D32) else Color(0xFFCCD8C8),
            radius = 6f,
            center = Offset(startX + index * 26f, y)
        )
    }
}
