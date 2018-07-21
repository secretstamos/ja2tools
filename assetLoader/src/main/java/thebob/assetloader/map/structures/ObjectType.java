/*
 * The MIT License
 *
 * Copyright 2017 the_bob.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package thebob.assetloader.map.structures;

import thebob.assetloader.map.MapLoader;
import thebob.assetloader.map.helpers.AutoLoadingMapStruct;
import thebob.assetloader.map.structures.legacy.OLD_OBJECTTYPE_101;

import static thebob.assetloader.map.core.MapData.xmlDataSource;

public class ObjectType extends AutoLoadingMapStruct {

    public final Unsigned16 usItem = new Unsigned16();
    public final Unsigned8 ubNumberOfObjects = new Unsigned8();
    public final Unsigned8 ubMission = new Unsigned8();
    public final Unsigned8 fFlags = new Unsigned8();

    @Override
    public String toString() {
        String itemName = "Nothing";
        if (usItem.get() != 0) {
            try {
                itemName = xmlDataSource.getItems().getITEM().get(usItem.get()).getSzItemName();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("ObjectType::toString() item id not found: " + usItem.get());
            }
        }

        return "ObjectType:\n\t"
                + "usItem: " + usItem.get() + " @" + usItem.offset() + " (" + itemName + ") \n\t"
                + "ubNumberOfObjects: " + ubNumberOfObjects.get() + " @" + ubNumberOfObjects.offset() + "\n\t"
                + "ubMission: " + ubMission.get() + " @" + ubMission.offset() + "\n\t"
                + "fFlags: " + fFlags.get() + " @" + fFlags.offset() + "\n-------------\n\t"
                + super.toString();
    }

    @Override
    public int getOffsetAdjustment() {
        return -1;
    }

    public void loadOld(OLD_OBJECTTYPE_101 old) {
        usItem.set(old.usItem.get());
        ubNumberOfObjects.set(old.ubNumberOfObjects.get());
        ubMission.set(old.ubMission.get());
        fFlags.set(old.fFlags.get());

        if (MapLoader.logEverything) {
            System.out.println("ObjectType::loadOld() -> " + toString());
        }
    }

}
