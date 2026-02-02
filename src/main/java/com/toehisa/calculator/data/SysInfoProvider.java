package com.toehisa.calculator.data;

import java.awt.*;

public class SysInfoProvider {

    public static class ScreenResolution {
        private GraphicsDevice[] graphicsDevices;

        private int screensCount;
        private double[] screensWidth;
        private double[] screensHeight;

        {
            try {
                graphicsDevices = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
                screensCount = graphicsDevices.length;
                screensWidth = new double[screensCount];
                screensHeight = new double[screensCount];
            } catch (HeadlessException e) {
                System.exit(0);
            }
        }

        public ScreenResolution() {
            fillScreensResolutionInfo();
        }

        public double getScreenWidth(int monitorIndex) {
            if (monitorIndex < 0 || monitorIndex > screensCount - 1) {return 1920;}
            return screensWidth[monitorIndex];
        }

        public double getScreenHeight(int monitorIndex) {
            if (monitorIndex < 0 || monitorIndex > screensCount - 1) {return 1080;}
            return screensHeight[monitorIndex];
        }

        public int getScreensCount() {
            return screensCount;
        }

        private void fillScreensResolutionInfo() {
            for (int i = 0; i < screensCount; i++) {
                DisplayMode mode = graphicsDevices[i].getDisplayMode();
                screensWidth[i] = mode.getWidth();
                screensHeight[i] = mode.getHeight();
                System.out.printf("Разрешение монитора %s: %dx%d\n", graphicsDevices[i].getIDstring(), mode.getWidth(), mode.getHeight());
            }
        }
    }

}
