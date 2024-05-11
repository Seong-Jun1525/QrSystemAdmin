package kr.ac.yuhan.cs.qradmin.util;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import soup.neumorphism.NeumorphButton;
import soup.neumorphism.NeumorphCardView;
import soup.neumorphism.NeumorphImageView;

public class ChangeMode {
    public static void applyMainTheme(View rootView, int mode) {
        int textColor;
        int buttonColor;

        if (mode == 0) {
            // DarkMode
            textColor = Color.WHITE;
            buttonColor = Color.BLACK;
        } else {
            // LightMode
            textColor = Color.rgb(0, 105, 97);
            buttonColor = Color.rgb(0, 174, 142);
        }

        setTextColor(rootView, textColor);
        setButtonColor(rootView, buttonColor);
    }
    public static void applySubTheme(View rootView, int mode) {
        int textColor;
        int buttonColor;

        if (mode == 1) {
            // DarkMode
            textColor = Color.WHITE;
            buttonColor = Color.BLACK;
        } else {
            // LightMode
            textColor = Color.rgb(0, 105, 97);
            buttonColor = Color.rgb(0, 174, 142);
        }

        setTextColor(rootView, textColor);
        setButtonColor(rootView, buttonColor);
    }

    private static void setTextColor(View view, int color) {
        if (view instanceof TextView) {
            // Change FontColor if view is TextView
            ((TextView) view).setTextColor(color);
        } else if (view instanceof ViewGroup) {
            // If the view is a ViewGroup, recursively change the color for all views in that ViewGroup
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                setTextColor(viewGroup.getChildAt(i), color);
            }
        }
    }

    private static void setButtonColor(View view, int backgroundColor) {
        if (view instanceof Button) {
            // Change FontColor if view is Button
            Button button = (Button) view;
            button.setBackgroundColor(backgroundColor);
            ColorStateList colorStateList = ColorStateList.valueOf(Color.WHITE);
            button.setTextColor(colorStateList); // Set the button's font color to always be white
        } else if (view instanceof ViewGroup) {
            // If the view is a ViewGroup, recursively change the color for all views in that ViewGroup
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                setButtonColor(viewGroup.getChildAt(i), backgroundColor);
            }
        }
    }

    public static void setDarkShadowCardView(View view) {
        if (view instanceof NeumorphCardView) {
            NeumorphCardView nCardView = (NeumorphCardView) view;
            nCardView.setShadowColorLight(Color.GRAY);
            nCardView.setShadowColorDark(Color.BLACK);
        }

        if (view instanceof NeumorphImageView) {
            NeumorphImageView nImageView = (NeumorphImageView) view;
            nImageView.setShadowColorLight(Color.GRAY);
            nImageView.setShadowColorDark(Color.BLACK);
        }

        if (view instanceof NeumorphButton) {
            NeumorphButton nButtonView = (NeumorphButton) view;
            nButtonView.setShadowColorLight(Color.GRAY);
            nButtonView.setShadowColorDark(Color.BLACK);
        }
    }

    public static void setLightShadowCardView(View view) {
        if (view instanceof NeumorphCardView) {
            NeumorphCardView nCardView = (NeumorphCardView) view;
            nCardView.setShadowColorLight(Color.WHITE);
            nCardView.setShadowColorDark(Color.rgb(217, 217, 217));
        }

        if (view instanceof NeumorphImageView) {
            NeumorphImageView nImageView = (NeumorphImageView) view;
            nImageView.setShadowColorLight(Color.WHITE);
            nImageView.setShadowColorDark(Color.rgb(217, 217, 217));
        }

        if (view instanceof NeumorphButton) {
            NeumorphButton nButtonView = (NeumorphButton) view;
            nButtonView.setShadowColorLight(Color.WHITE);
            nButtonView.setShadowColorDark(Color.rgb(217, 217, 217));
        }
    }

    public static void setColorFilterDark(View view) {
        if (view instanceof ImageView) {
            ImageView imageView = (ImageView) view;
            imageView.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
        }
        if (view instanceof NeumorphImageView) {
            NeumorphImageView nImageView = (NeumorphImageView) view;
            nImageView.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
        }
    }

    public static void setColorFilterLight(View view) {
        if (view instanceof ImageView) {
            ImageView imageView = (ImageView) view;
            imageView.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
        }
        if (view instanceof NeumorphImageView) {
            NeumorphImageView nImageView = (NeumorphImageView) view;
            nImageView.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
        }
    }
}
