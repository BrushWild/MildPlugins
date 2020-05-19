/*
 * Copyright (c) 2020 BrushWild <https://github.com/BrushWild>
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

package net.runelite.client.plugins.externals.xpdropnotification;

// Imports
//import com.google.inject.Provides;
//import java.io.File;
//import java.io.IOException;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.SoundEffectID;
import net.runelite.api.SoundEffectVolume;
import net.runelite.api.events.GameTick;
//import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
//import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.PluginType;
import org.pf4j.Extension;

@Extension
@PluginDescriptor(
		name = "Xp Drop Notification",
		enabledByDefault = false,
		description = "Plays a sound when xp drops. Wub wub wuuub wub wubbbb...",
		tags = {"skilling", "tick", "notification", "xp", "drop"},
		type = PluginType.MISCELLANEOUS
)
@Slf4j
public class XpDropNotification extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private XpDropNotificationConfig config;

	// Init member variables

//    @Provides
//    XpDropNotification provideConfig(ConfigManager configManager)
//    {
//        return configManager.getConfig(XpDropNotificationConfig.class);
//    }

	@Subscribe
	public void onGameTick(GameTick tick)
	{
		// Check for xp drops and play a sound effect when it happens
		if (config.testItem())
		{
			client.playSoundEffect(SoundEffectID.GE_INCREMENT_PLOP, SoundEffectVolume.MEDIUM_HIGH);
		}
	}
}