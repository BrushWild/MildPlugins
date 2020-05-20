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
package net.runelite.client.plugins.externals.xpdropsounds;

import net.runelite.api.SoundEffectID;
import net.runelite.api.SoundEffectVolume;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("XpDropSounds")

public interface XpDropSoundsConfig extends Config
{
	public enum Volume
	{
		LOW("Low", SoundEffectVolume.LOW),
		MEDIUM_LOW("Medium Low", SoundEffectVolume.MEDIUM_LOW),
		MEDIUM_HIGH("Medium High", SoundEffectVolume.MEDIUM_HIGH),
		HIGH("High", SoundEffectVolume.HIGH);

		private final String name;
		private final int vol;
		Volume(String name, int vol)
		{
			this.name = name;
			this.vol = vol;
		}

		public String toString()
		{
			return this.name;
		}

		public int toInt()
		{
			return this.vol;
		}
	}

	public enum SoundEffectMapping
	{
		BOOP("Boop", SoundEffectID.UI_BOOP),
		TICK("Tick", SoundEffectID.GE_INCREMENT_PLOP),
		TOCK("Tock", SoundEffectID.GE_DECREMENT_PLOP),
		DINGALING("Dingaling", SoundEffectID.GE_ADD_OFFER_DINGALING),
		GE_BLOOP("Ge Collectiong Bloop", SoundEffectID.GE_COLLECT_BLOOP),
		BOING("Magic Splash Boing", SoundEffectID.MAGIC_SPLASH_BOING),
		WOOSH("Fire Woosh", SoundEffectID.FIRE_WOOSH);

		private final String name;
		private final int soundId;
		SoundEffectMapping(String name, int soundId)
		{
			this.name = name;
			this.soundId = soundId;
		}

		public String toString()
		{
			return this.name;
		}

		public int toInt()
		{
			return this.soundId;
		}
	}

	@ConfigItem(
			keyName = "soundVolume",
			name = "Volume",
			description = "I'm not sure this works :d",
			position = 0
	)
	default Volume GetSoundVolume()
	{
		return Volume.MEDIUM_HIGH;
	}

	@ConfigItem(
			keyName = "soundId",
			name = "Sound",
			description = "Changes the sound played for xp drops."
	)
	default SoundEffectMapping GetSoundEffectMapping()
	{
		return SoundEffectMapping.TICK;
	}

	@ConfigItem(
			keyName = "onTick",
			name = "On Tick",
			description = "Debug the sound id."
	)
	default boolean onTick()
	{
		return false;
	}
}