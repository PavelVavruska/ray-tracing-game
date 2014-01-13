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

/**
 *
 * @author Pavel Vavruška <vavruska.pavel@gmail.com>
 */
public class Camera extends GameUnit {

    private boolean isOn, isMoving;

    public Camera(double x, double y, double vx, double vy) {
        super(x, y, vx, vy);
    }

    public boolean isMoving() {
        return isMoving;
    }

    public boolean isOn() {
        return isOn;
    }

    public void makeOn() {
        isOn = true;
    }

    public void makeOff() {
        isOn = false;
    }

    public void moveOn() {
        isMoving = true;
    }

    public void moveOff() {
        isMoving = false;
    }

    public void addVelocityX(double velX) {
        if (Math.abs(getVelocityX() + velX) <= 1d) {
            setVelocityX(getVelocityX() + velX);
        }
    }

    public void addVelocityY(double velY) {
        if (Math.abs(getVelocityY() + velY) <= 1d) {
            setVelocityY(getVelocityY() + velY);
        }

    }

    public void calculate(int minX, int minY, int maxX, int maxY) {
        setCorX(getCorX() + getVelocityX());
        setCorY(getCorY() + getVelocityY());
    }
}
