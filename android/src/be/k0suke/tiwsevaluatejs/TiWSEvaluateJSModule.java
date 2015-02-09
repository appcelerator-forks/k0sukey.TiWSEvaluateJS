/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2010 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package be.k0suke.tiwsevaluatejs;

import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.KrollRuntime;
import org.appcelerator.kroll.annotations.Kroll;

import org.appcelerator.titanium.TiApplication;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.kroll.common.TiConfig;

import java.net.URI;
import java.net.URISyntaxException;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

@Kroll.module(name="TiWSEvaluateJS", id="be.k0suke.tiwsevaluatejs")
public class TiWSEvaluateJSModule extends KrollModule
{

	// Standard Debugging variables
	private static final String LCAT = "TiWSEvaluateJSModule";
	private static final boolean DBG = TiConfig.LOGD;

	// You can define constants with @Kroll.constant, for example:
	// @Kroll.constant public static final String EXTERNAL_NAME = value;

	public TiWSEvaluateJSModule()
	{
		super();
	}

	@Kroll.onAppCreate
	public static void onAppCreate(TiApplication app)
	{
		Log.d(LCAT, "inside onAppCreate");
		// put module init code that needs to run when the application is created


		URI uri;
		try {
			uri = new URI(TiApplication.getInstance().getAppProperties().getString("evaluate-host", ""));
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}

		WebSocketClient ws = new WebSocketClient(uri, new Draft_17()) {
			@Override
			public void onOpen(ServerHandshake handshake) {
				Log.d(LCAT, "onOpen");
			}

			@Override
			public void onClose(int code, String reason, boolean remote) {
				Log.d(LCAT, "onClose");
			}

			@Override
			public void onError(Exception ex) {
				Log.d(LCAT, "onError");
			}

			@Override
			public void onMessage(String message) {
				Log.d(LCAT, "onMessage");
				KrollRuntime.getInstance().evalString(message);
				this.send(message);
			}
		};
		ws.connect();
	}

	// Methods
	@Kroll.method
	public String example()
	{
		Log.d(LCAT, "example called");
		return "hello world";
	}

	// Properties
	@Kroll.getProperty
	public String getExampleProp()
	{
		Log.d(LCAT, "get example property");
		return "hello world";
	}


	@Kroll.setProperty
	public void setExampleProp(String value) {
		Log.d(LCAT, "set example property: " + value);
	}
}