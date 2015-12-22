package com.mvm.lostinspace.components;

import com.badlogic.ashley.core.Component;

public class MovementComponent implements Component {
	public float vel_x = 0.0f;
    public float vel_y = 0.0f;

    public float acc_x = 0.0f;
    public float acc_y = 0.0f;
    
    public float resistance = 0f; 
    public float resistance_acc = 30f;
    
    public float max_vel_x = 100;
    public float max_vel_y = 100;
    public float min_vel_x = -100;
    public float min_vel_y = -100;
     
    
    public float acceleration_speed = 50;

}
