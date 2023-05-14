package com.infinity.coins.presentation.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infinity.coins.presentation.ui.theme.BackgroundInterfaceSecondary
import com.infinity.coins.presentation.ui.theme.GreenPantone
import com.infinity.coins.presentation.ui.theme.IconsSecondary
import com.xpenzi.R

@Composable
@Preview
fun TransactionCard() {
    var expanded by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if(expanded) 180f else 0f
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Column {
            Row(modifier = Modifier.padding(16.dp)) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(BackgroundInterfaceSecondary)
                ) {
                    Image(
                        modifier = Modifier
                            .size(24.dp)
                            .align(Alignment.Center),
                        painter = painterResource(id = R.drawable.ic_amazon),
                        contentDescription = "Icon"
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Amazon India",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Mon, 8th Oct - 12:32",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "₹422.50",
                        style = MaterialTheme.typography.titleMedium,
                        color = GreenPantone
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Icon(
                        modifier = Modifier
                            .clip(RoundedCornerShape(36.dp))
                            .size(32.dp)
                            .rotate(rotationState)
                            .clickable {
                                expanded = !expanded
                            },
                        tint = IconsSecondary,
                        imageVector = Icons.Rounded.ArrowDropDown,
                        contentDescription = "Details"
                    )
                }
            }
            if (expanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Separator()
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = "ICICI Bank Acct xx189 debited for Rs. 590.00 on 21st April; AIRTELINDIA credited. UPI: 311109641123. Call 18002662 for dispute. SMS BLOCK 189 to 9215676766",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}