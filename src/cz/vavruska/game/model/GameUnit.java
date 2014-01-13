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
public class GameUnit {

    private double corX, corY, velocityX, velocityY;
    private short timeToDie;
    private boolean visible = true;

    public GameUnit(double x, double y, double vx, double vy) {
        corX = x;
        corY = y;
        velocityX = vx;
        velocityY = vy;
        timeToDie = (short) 500;
        visible = true;
    }

    public double getCorX() {
        return corX;
    }

    public void setCorX(double corX) {
        this.corX = corX;
    }

    public double getCorY() {
        return corY;
    }

    public void setCorY(double corY) {
        this.corY = corY;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public short getTimeToDie() {
        return timeToDie;
    }

    public void setTimeToDie(short timeToDie) {
        this.timeToDie = timeToDie;
    }

    public boolean getVisibility() {
        return visible;
    }

    public void setVisibility(boolean visible) {
        this.visible = visible;
    }

}
