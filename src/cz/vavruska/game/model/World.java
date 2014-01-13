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
package cz.vavruska.game.model;

import java.util.ArrayList;

/**
 *
 * @author Pavel Vavruška <vavruska.pavel@gmail.com>
 */
public class World {

    private static final ArrayList<Wall> walls = new ArrayList<>();
    private static final ArrayList<Photon> photons = new ArrayList<>();
    private static final Camera playerCamera = new Camera(200d, 200d, 0.1d, 0.1d);
    private static int minX, maxX, minY, maxY;

    public World(int minX, int maxX, int minY, int maxY) {
        walls.clear();
        photons.clear();
        World.minX = minX;
        World.maxX = maxX;
        World.minY = minY;
        World.maxY = maxY;
    }

    public static int getMinX() {
        return minX;
    }

    public static int getMaxX() {
        return maxX;
    }

    public static int getMinY() {
        return minY;
    }

    public static int getMaxY() {
        return maxY;
    }

    public static void addWall(Wall wall) {
        walls.add(wall);
    }

    public static int getCountOfWalls() {
        return walls.size();
    }

    public static Wall getWallById(int id) {
        return walls.get(id);
    }

    public static void addPhoton(Photon photon) {
        photons.add(photon);
    }

    public static int getCountOfPhotons() {
        return photons.size();
    }

    public static Photon getPhotonById(int id) {
        return photons.get(id);
    }

    public static Camera getPlayerCamera() {
        return playerCamera;
    }

    public static void removePhoton(Photon photon) {
        photons.remove(photon);
    }

}
