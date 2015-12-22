package com.mvm.lostinspace.systems;


import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mvm.lostinspace.components.MovementComponent;
import com.mvm.lostinspace.components.SmoothCamComponent;
import com.uwsoft.editor.renderer.components.DimensionsComponent;
import com.uwsoft.editor.renderer.components.TransformComponent;

public class MovementSystem extends IteratingSystem {
	private ComponentMapper<TransformComponent> pm = ComponentMapper.getFor(TransformComponent.class);
	private ComponentMapper<MovementComponent> mm = ComponentMapper.getFor(MovementComponent.class);
	private ComponentMapper<DimensionsComponent> dm = ComponentMapper.getFor(DimensionsComponent.class);
	private ComponentMapper<SmoothCamComponent> cm = ComponentMapper.getFor(SmoothCamComponent.class);
	
	private Viewport viewport;
	
	@SuppressWarnings("unchecked")
	public MovementSystem (Viewport viewport) {
		super(Family.all(TransformComponent.class, MovementComponent.class).get());
		this.viewport = viewport;
	}

	@Override
	public void processEntity (Entity entity, float deltaTime) {
		TransformComponent position = pm.get(entity);
		MovementComponent movement = mm.get(entity);
		SmoothCamComponent cam = cm.get(entity);
				
		movement.vel_x += movement.acc_x * deltaTime;
		movement.vel_y += movement.acc_y * deltaTime;	
		
		if (movement.vel_x > movement.max_vel_x)  
			movement.vel_x = movement.max_vel_x;
		if (movement.vel_x < movement.min_vel_x)  
			movement.vel_x = movement.min_vel_x;
		if (movement.vel_y > movement.max_vel_y)  
			movement.vel_y = movement.max_vel_y;
		if (movement.vel_y < movement.min_vel_y)  
			movement.vel_y = movement.min_vel_y;
				
							
		position.x += movement.vel_x * deltaTime;
		position.y += movement.vel_y * deltaTime;
		
		if (cam != null) {
			
			DimensionsComponent dc = entity.getComponent(DimensionsComponent.class);			
			cam.smoothCamSubject.setPosition(position.x + dc.width + dc.width / 2, position.y + dc.height / 2 );		
			cam.smoothCamSubject.setVelocity(movement.vel_x / movement.max_vel_x, movement.vel_y / movement.max_vel_y);
		}				
		
	}
}
