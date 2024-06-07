package fr.gabrielabgrall.gnpengine;

import fr.gabrielabgrall.gnpengine.gameobject.GNPObject;
import fr.gabrielabgrall.gnpengine.utils.Clock;

import java.util.List;

public class PhysicsEngine extends Thread {

    protected boolean running = true;
    protected double ups;
    protected long elapsed;
    private final List<GNPObject> GNPObjects;

    protected PhysicsEngine(double ups, List<GNPObject> GNPObjects) throws IllegalArgumentException {
        if(ups < 0) throw new IllegalArgumentException("UPS must be a positive number");

        this.ups = ups;
        this.GNPObjects = GNPObjects;
    }

    @Override
    public void run() {
        Clock clock = new Clock("PhysicsEngine");
        while(running) {
            update();
            clock.tick(ups);
        }
    }

    protected void update() {
        GNPObjects.forEach(gameObject -> {
            gameObject.update(elapsed);
        });
    };

    public void setUps(float ups) {
        this.ups = ups;
    }

    public double getUps() {
        return ups;
    }
}