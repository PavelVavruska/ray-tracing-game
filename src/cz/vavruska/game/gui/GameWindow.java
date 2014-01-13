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

import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JFrame;

/**
 *
 * @author Pavel Vavruška <vavruska.pavel@gmail.com>
 */
public class GameWindow extends JFrame {

    long lastFps;

    public GameWindow(int maxX, int maxY) {

        this.setPreferredSize(new Dimension(maxX + 5, maxY + 25)); // fix for Windows
        this.setLocationRelativeTo(null);
        this.getContentPane().add(new Renderer());
        this.pack();
        this.setVisible(true);
        this.setResizable(false);
        this.setTitle("Ray Tracing pre-alpha");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lastFps = System.nanoTime();

        // Set cursor shape
        this.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
    }
}
