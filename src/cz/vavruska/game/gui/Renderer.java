/*
 * The MIT License
 *
 * Copyright 2014 Pavel Vavruška <vavruska.pavel@gmail.com>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package cz.vavruska.game.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;

import cz.vavruska.game.model.World;

/**
 *
 * @author Pavel Vavruška <vavruska.pavel@gmail.com>
 */
public class Renderer extends JComponent {

    long lastFps;
    private static double frameTime;
    int numberOfEnemies;

    BufferedImage drawedImage, writeImage;
    Graphics2D drawer;

    public Renderer() {
        drawedImage = new BufferedImage(World.getMaxX(), World.getMaxY(), BufferedImage.TYPE_3BYTE_BGR);
        writeImage = new BufferedImage(World.getMaxX(), World.getMaxY(), BufferedImage.TYPE_3BYTE_BGR);
    }

    @Override
    public void paint(Graphics g) {
        drawEverything();
        g.drawImage(drawedImage, 0, 0, null);
    }

    public void drawPixel(BufferedImage writeImagex, int x, int y, int color) {

        if (x > 0 && x < World.getMaxX() && y > 0 && y < World.getMaxY()) {
            writeImagex.setRGB(x, y, color);
        }
    }

    private void swapBuffers() {
        BufferedImage helper = drawedImage;
        drawedImage = writeImage;
        writeImage = helper;
    }

    public void drawWall(int x, int y, int size, int color) {
        for (int xx = x - size; xx <= x + size; xx++) {
            drawPixel(writeImage, xx, y, color);
        }
        for (int yy = y - size; yy <= y + size; yy++) {
            drawPixel(writeImage, x, yy, color);
        }
    }

    public void drawBall(int x, int y, double vx, double vy, int size, int color, int detail) {
        vx /= detail;
        vy /= detail;
        for (int detailLevel = 1; detailLevel <= detail; detailLevel++) {
            drawPixel(writeImage, (int) (x - vx * detailLevel),
                    (int) (y - vy * detailLevel),
                    color);
        }
    }

    public void drawEverything() {

        double deltaX = World.getPlayerCamera().getCorX();
        double deltaY = -World.getPlayerCamera().getCorY();

        // Clear content        
        writeImage.getGraphics().clearRect(0, 0, World.getMaxX(), World.getMaxY());

        // Draw Camera       
        drawPixel(writeImage, (int) (World.getMaxX() / 2), World.getMaxY() / 2, Color.WHITE.getRGB());

        // Draw bullets
        for (int i = 0; i < World.getCountOfPhotons() / 1.02; i++) {
            if (World.getPhotonById(i) != null) {
                int colorOfLight = World.getPhotonById(i).getTimeToDie() / 2;

                if (colorOfLight < 0) {
                    colorOfLight = 0;
                }
                if (colorOfLight > 255) {
                    colorOfLight = 255;
                }

                int color = new Color(colorOfLight, colorOfLight, 0).getRGB();

                drawBall((int) (World.getPhotonById(i).getCorX() + deltaX),
                        (int) (World.getPhotonById(i).getCorY() + deltaY),
                        World.getPhotonById(i).getVelocityX(),
                        World.getPhotonById(i).getVelocityY(),
                        5,
                        color,
                        5);
            }
        }

        // info panel block
        Font font = new Font("Serif", Font.PLAIN, 12);
        writeImage.getGraphics().setFont(font);
        frameTime = System.nanoTime() - lastFps;

        double fps = 1000000000d / frameTime; // Count of rendered frames in one second
        writeImage.getGraphics().drawString("FPS: " + String.valueOf((int) fps), 10, 50);
        writeImage.getGraphics().drawString("Walls: " + String.valueOf((int) World.getCountOfWalls()), 10, 80);

        swapBuffers();
        lastFps = System.nanoTime();
    }

    public static double getFrameTime() {
        return frameTime;
    }

}
