package com.mvm.lostinspace;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mvm.lostinspace.components.MovementComponent;
import com.mvm.lostinspace.components.PlayerComponent;
import com.mvm.lostinspace.components.SmoothCamComponent;
import com.mvm.lostinspace.components.SmoothCamPointComponent;
import com.mvm.lostinspace.systems.CameraSystem;
import com.mvm.lostinspace.systems.InputSystem;
import com.mvm.lostinspace.systems.MovementSystem;
import com.mvm.smoothcam.SmoothCamDebugRenderer;
import com.mvm.smoothcam.SmoothCamWorld;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.utils.ItemWrapper;

public class LostInSpace extends ApplicationAdapter {
	
	SceneLoader sl;
	Viewport viewport;
	SmoothCamWorld scw;
	SmoothCamDebugRenderer scDebug;
	
	public static TweenManager tweenmanager = new TweenManager();
	
	@Override
	public void create () {
		
		
		viewport = new FitViewport(1920/2, 1080/2);		
		sl = new SceneLoader();			
		sl.loadScene("MainScene", viewport);
		
		ItemWrapper root = new ItemWrapper(sl.getRoot());
		
		Entity playerEntity = root.getChild("Player").getEntity();
		playerEntity.add(new PlayerComponent());
		playerEntity.add(new MovementComponent());
		playerEntity.add(new SmoothCamComponent());
		
		Player player = new Player();
		root.getChild("Player").addScript(player);
		
		Entity scp = root.getChild("scp").getEntity();
		TransformComponent t = scp.getComponent(TransformComponent.class);		
		scp.add(new SmoothCamPointComponent(t.x, t.y));
				
		scw = new SmoothCamWorld();		
		scDebug = new SmoothCamDebugRenderer();				
		SmoothCamComponent c = playerEntity.getComponent(SmoothCamComponent.class);
		scw.setSubject(c.smoothCamSubject);
		scw.addPoint(scp.getComponent(SmoothCamPointComponent.class).scp);
		
		
		sl.getEngine().addSystem(new MovementSystem(viewport));	
		sl.getEngine().addSystem(new CameraSystem(scw));
		
		
		
		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		
		//inputMultiplexer.addProcessor(hud);
		inputMultiplexer.addProcessor(new InputSystem(viewport,playerEntity));	
		
				
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.1f,0.1f,0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		tweenmanager.update(Gdx.graphics.getDeltaTime());
		scw.update();
		
		viewport.getCamera().update();				
		scDebug.render(scw, viewport.getCamera().combined);	
		sl.getEngine().update(Gdx.graphics.getDeltaTime());
		
		
	}
}
