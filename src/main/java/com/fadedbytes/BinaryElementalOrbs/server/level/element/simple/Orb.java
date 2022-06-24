package com.fadedbytes.BinaryElementalOrbs.server.level.element.simple;

import com.fadedbytes.BinaryElementalOrbs.server.level.Location;

public class Orb extends SimpleElement {

    public Orb() {
        super(100.0f);
    }

    public Orb(Location loc) {
        super(loc, 100.0f);
    }

}
