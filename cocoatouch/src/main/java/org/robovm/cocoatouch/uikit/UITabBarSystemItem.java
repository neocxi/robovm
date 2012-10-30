/*
 * Copyright (C) 2012 RoboVM
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.robovm.cocoatouch.uikit;

import org.robovm.rt.bro.ValuedEnum;

public enum UITabBarSystemItem implements ValuedEnum {
    More(0),
    Favorites(1),
    Featured(2),
    TopRated(3),
    Recents(4),
    Contacts(5),
    History(6),
    Bookmarks(7),
    Search(8),
    Downloads(9),
    MostRecent(10),
    MostViewed(11);

    private final int n;

    private UITabBarSystemItem(int n) { this.n = n; }
    public int value() { return n; }
}