/*
 * Copyright (c) 2018, OpenOSRS <https://github.com/open-osrs>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package net.runelite.client.plugins.neverlogout;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.GameTick;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.PluginType;
import org.pf4j.Extension;
import java.util.Random;

@Extension
@PluginDescriptor(
	name = "Never Logout",
	enabledByDefault = true,
	description = "Overrides the 5 minute AFK logout timer.",
	tags = {"never log", "idle", "logout", "log", "never"},
	type = PluginType.UTILITY
)

@Slf4j
public class NeverLogoutPlugin extends Plugin
{
	@Inject
	private Client client;

	private Random random = new Random();
	private long randomDelay;

	private boolean toggle = false;

	@Override
	protected void startUp()
	{
		randomDelay = randomDelay();
	}

//	@Inject
//	private NeverLogoutConfig config;
//
//	@Provides
//	NeverLogoutConfig provideConfig(ConfigManager configManager)
//	{
//		return configManager.getConfig(NeverLogoutConfig.class);
//	}

	@Subscribe
	private void onGametick(GameTick gameTick)
	{
		if (checkIdleLogout())
		{
			String info = String.format("Set idle tick for %s", (toggle ? "keyboard" : "mouse"));
			log.info(info);
			if (toggle)
			{
				client.setKeyboardIdleTicks(0);
			}
			else
			{
				client.setMouseIdleTicks(0);
			}
			randomDelay = randomDelay();
		}
	}

	private boolean checkIdleLogout()
	{
		return Math.max(client.getKeyboardIdleTicks(), client.getMouseIdleTicks()) >= randomDelay;
	}

	private long randomDelay()
	{
		double nextDouble = clamp(Math.round(random.nextDouble() * 10000));
		String info = String.format("New random delay: %f ticks", nextDouble);
		log.info(info);
		toggle = random.nextBoolean();
		return (long) nextDouble;
	}

	private static double clamp(double val)
	{
		return Math.max(1, Math.min(10000, val));
	}
}
