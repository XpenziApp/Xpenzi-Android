package com.infinity.coins.presentation.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.infinity.coins.presentation.ui.theme.BackgroundInterfaceBg
import com.infinity.coins.presentation.ui.theme.SeparatorDark
import com.infinity.coins.presentation.ui.theme.TextBrand
import com.infinity.coins.presentation.ui.theme.TextPrimary
import com.infinity.coins.presentation.ui.theme.TextSecondary
import com.infinity.coins.presentation.vm.LoginViewModel
import com.xpenzi.R

const val MAX_CHARS = 10

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onValidPhoneNumber: (String) -> Unit,
    onInvalidPhoneNumber: () -> Unit
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.reports))
    Column(modifier = Modifier.fillMaxSize()) {
        LottieAnimation(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.5f)
                .padding(top = 0.dp),
            iterations = 100,
            composition = composition
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            text = "Reports",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.displayLarge,
            color = TextBrand,
            fontSize = 32.sp
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            text = "Track your spends in details",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displaySmall,
            color = TextSecondary,
            fontSize = 18.sp
        )
        Box(
            modifier = Modifier.weight(1f),
        ) {
            PhoneNumberComponent(
                modifier = Modifier.align(Alignment.BottomCenter),
                viewModel = viewModel,
                onValidPhoneNumber = onValidPhoneNumber,
                onInvalidPhoneNumber = onInvalidPhoneNumber
            )
        }
    }
}

@Composable
@ExperimentalMaterial3Api
fun PhoneNumberComponent(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel,
    onValidPhoneNumber: (String) -> Unit,
    onInvalidPhoneNumber: () -> Unit,
) {
    val focusManager = LocalFocusManager.current

    Column(modifier = modifier) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, SeparatorDark)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = BackgroundInterfaceBg)
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .size(30.dp),
                    painter = painterResource(id = R.drawable.ic_india),
                    contentDescription = ""
                )
                Text(
                    modifier = Modifier
                        .padding(start = 8.dp),
                    text = "+91",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextPrimary,
                    fontSize = 18.sp
                )
                TextField(
                    singleLine = true,
                    value = viewModel.selectedPhone.value,
                    textStyle = MaterialTheme.typography.bodySmall,
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.enter_your_mobile_number),
                            style = MaterialTheme.typography.bodySmall,
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = TextPrimary,
                        containerColor = BackgroundInterfaceBg,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { focusManager.clearFocus() }
                    ),
                    onValueChange = {
                        if (it.length <= MAX_CHARS) {
                            viewModel.selectedPhone.value = it
                        }
                        if (viewModel.selectedPhone.value.length == MAX_CHARS) {
                            onValidPhoneNumber(viewModel.selectedPhone.value)
                        } else {
                            onInvalidPhoneNumber()
                        }
                    }
                )
            }
        }
    }
}