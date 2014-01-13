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
public class Photon extends GameUnit {

    public Photon(double x, double y, double vx, double vy) {
        super(x, y, vx, vy);
    }

    public void calculate(double gravity) {

        if (this.getVisibility()) {
            this.setCorX(this.getCorX() + this.getVelocityX());
            this.setVelocityY(this.getVelocityY() / gravity);
            this.setCorY(this.getCorY() + this.getVelocityY());

            this.setTimeToDie((short) (this.getTimeToDie() - 10));
            if (this.getTimeToDie() <= 0) {
                this.setVisibility(false);
                stopPhoton();
            }
            // calculate collision if the bullet is visible
            calculateCollision();
        }
    }

    public void calculateCollision() {
        if (this.getVelocityX() != 0 && this.getVelocityY() != 0) {
            for (int i = 0; i < World.getCountOfWalls(); i++) {
                if (World.getWallById(i).getCorX() > this.getCorX() - 20
                        && World.getWallById(i).getCorX() < this.getCorX() + 20
                        && World.getWallById(i).getCorY() > this.getCorY() - 20
                        && World.getWallById(i).getCorY() < this.getCorY() + 20) {
                    this.setTimeToDie((short) (this.getTimeToDie() - 500));
                    stopPhoton();

                }
            }
        }
    }

    public void stopPhoton() {
        this.setVelocityX(0d);
        this.setVelocityY(0d);
        this.setVisibility(false);
        World.removePhoton(this);
    }
}
