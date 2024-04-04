package com.packapps.design.animations

import android.view.View
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce


fun View.applySpringAnimation() {
    val springAnimation = SpringAnimation(this, DynamicAnimation.TRANSLATION_Y, 0f)

    val springForce = SpringForce().apply {
        finalPosition = 50f
        stiffness = SpringForce.STIFFNESS_LOW
        dampingRatio = SpringForce.DAMPING_RATIO_HIGH_BOUNCY

    }
    springAnimation.spring = springForce
    springAnimation.start()
}
