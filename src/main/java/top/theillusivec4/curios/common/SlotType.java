/*
 * Copyright (c) 2018-2020 C4
 *
 * This file is part of Curios, a mod made for Minecraft.
 *
 * Curios is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Curios is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Curios.  If not, see <https://www.gnu.org/licenses/>.
 */

package top.theillusivec4.curios.common;

import java.util.Objects;
import net.minecraft.util.ResourceLocation;
import top.theillusivec4.curios.api.type.ISlotType;

public final class SlotType implements ISlotType {

  private final String identifier;
  private final int priority;
  private final int size;
  private final boolean locked;
  private final boolean visible;
  private final boolean cosmetic;
  private final ResourceLocation icon;

  private SlotType(Builder builder) {
    this.identifier = builder.identifier;
    this.priority = builder.priority;
    this.size = builder.size;
    this.locked = builder.locked;
    this.visible = builder.visible;
    this.cosmetic = builder.cosmetic;
    this.icon = builder.icon;
  }

  @Override
  public String getIdentifier() {
    return this.identifier;
  }

  @Override
  public ResourceLocation getIcon() {
    return this.icon;
  }

  @Override
  public int getPriority() {
    return this.priority;
  }

  @Override
  public int getSize() {
    return this.size;
  }

  @Override
  public boolean isLocked() {
    return this.locked;
  }

  @Override
  public boolean isVisible() {
    return this.visible;
  }

  @Override
  public boolean hasCosmetic() {
    return this.cosmetic;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SlotType that = (SlotType) o;
    return identifier.equals(that.identifier);
  }

  @Override
  public int hashCode() {
    return Objects.hash(identifier);
  }

  @Override
  public int compareTo(ISlotType otherType) {

    if (this.priority == otherType.getPriority()) {
      return this.identifier.compareTo(otherType.getIdentifier());
    } else if (this.priority > otherType.getPriority()) {
      return 1;
    } else {
      return -1;
    }
  }

  public static class Builder {

    private final String identifier;
    private int priority = 100;
    private int size = 1;
    private boolean locked = false;
    private boolean visible = true;
    private boolean cosmetic = true;
    private ResourceLocation icon = null;

    public Builder(String identifier) {
      this.identifier = identifier;
    }

    public Builder icon(ResourceLocation icon) {
      this.icon = icon;
      return this;
    }

    public Builder priority(int priority) {
      return priority(priority, false);
    }

    public Builder priority(int priority, boolean force) {
      this.priority = force ? priority : Math.min(this.priority, priority);
      return this;
    }

    public Builder size(int size) {
      return size(size, false);
    }

    public Builder size(int size, boolean force) {
      this.size = force ? size : Math.max(this.size, size);
      return this;
    }

    public Builder locked(boolean locked) {
      return locked(locked, false);
    }

    public Builder locked(boolean locked, boolean force) {
      this.locked = force ? locked : this.locked || locked;
      return this;
    }

    public Builder visible(boolean visible) {
      return visible(visible, false);
    }

    public Builder visible(boolean visible, boolean force) {
      this.visible = force ? visible : this.visible && visible;
      return this;
    }

    public Builder hasCosmetic(boolean cosmetic) {
      return hasCosmetic(cosmetic, false);
    }

    public Builder hasCosmetic(boolean cosmetic, boolean force) {
      this.cosmetic = force ? cosmetic : this.cosmetic && cosmetic;
      return this;
    }

    public SlotType build() {
      return new SlotType(this);
    }
  }
}
