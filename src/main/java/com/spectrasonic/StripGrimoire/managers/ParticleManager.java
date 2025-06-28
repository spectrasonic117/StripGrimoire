package com.spectrasonic.StripGrimoire.managers;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class ParticleManager {

    public static void playStripParticles(Player target) {
        Location center = target.getLocation();
        
        // Explosion-like particles
        target.getWorld().spawnParticle(
            Particle.WAX_ON,
            center,
            30,
            0.5, 0.5, 0.5,
            0.1
        );

        // Purple helix particles
        for (int i = 0; i < 20; i++) {
            double angle = 2 * Math.PI * i / 20;
            double x = 0.5 * Math.cos(angle);
            double z = 0.5 * Math.sin(angle);
            
            // Create a vector that moves upward while rotating
            Vector offset = new Vector(x, i * 0.05, z);
            
            Location particleLoc = center.clone().add(offset);
            
            target.getWorld().spawnParticle(
                Particle.DUST,
                particleLoc,
                1,
                new Particle.DustOptions(
                    Color.fromRGB(148, 0, 211), // Purple color
                    1.0f
                )
            );
        }

        // Additional effect - sparkles at feet
        target.getWorld().spawnParticle(
            Particle.SCULK_SOUL,
            center.getX(), center.getY(), center.getZ(),
            15,
            0.3, 0, 0.3,
            0.1
        );
    }
}