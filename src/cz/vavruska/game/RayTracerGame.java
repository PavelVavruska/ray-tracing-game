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

package cz.vavruska.game;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;

import cz.vavruska.game.model.Wall;
import cz.vavruska.game.model.Photon;
import cz.vavruska.game.model.World;

import cz.vavruska.game.gui.GameWindow;

/**
 *
 * @author Pavel Vavruška <vavruska.pavel@gmail.com>
 */
public class RayTracerGame {

    public static void initWorld() {
        new World(0, 600, 0, 600);

        for (int lineY = 1; lineY < 8; lineY++) {
            for (int lineX = 1; lineX < 8; lineX++) {
                double myX = World.getMaxX() - 100 * lineX;
                double myY = World.getMaxY() + 150 - 100 * lineY;
                World.addWall(new Wall(myX, myY, 1d, 1d));
            }
        }
    }

    public static void engineRun() {
        // init World
        initWorld();

        // Init game window
        GameWindow gameWindow = new GameWindow(World.getMaxX(), World.getMaxY());

        // Game loop
        while (true) {
            gameWindow.repaint();


            PointerInfo a = MouseInfo.getPointerInfo();
            Point point = new Point(a.getLocation());

            double whereIsMouseX = point.getX() - gameWindow.getX();
            double whereIsMouseY = point.getY() - gameWindow.getY();

            double positionRelativeToCameraX = World.getMaxX() / 2 - whereIsMouseX;
            double positionRelativeToCameraY = World.getMaxY() / 2 - whereIsMouseY;

            double gunPositionVelocityX = positionRelativeToCameraX;
            double gunPositionVelocityY = positionRelativeToCameraY;

            // Ready for moving?
            if (true) {
                double vx = gunPositionVelocityX / (10d);
                double vy = gunPositionVelocityY / (10d);
                World.getPlayerCamera().addVelocityX(vx / 1000);
                World.getPlayerCamera().addVelocityY(-vy / 1000);

            }

            // Move gun
            World.getPlayerCamera().calculate(World.getMinX(), World.getMinY(), World.getMaxX(), World.getMaxY());

            for (double theta = 0.0001; theta < 6.26; theta = theta + 0.015d) {

                double vx = Math.cos(theta) * 5;
                double vy = Math.sin(theta) * 5;

                Photon bullet = new Photon(World.getMaxX() / 2 - World.getPlayerCamera().getCorX(), World.getMaxY() / 2 + World.getPlayerCamera().getCorY(), -vx, -vy);
                World.addPhoton(bullet);
            }

            // Movement of bullets
            for (int i = 0; i < World.getCountOfPhotons(); i++) {
                if (World.getPhotonById(i).getVisibility()) {
                    World.getPhotonById(i).calculate(1);
                }
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        engineRun();
    }

}
