/*
 * This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.neatmonster.nocheatplus.compat.blocks.init;

import org.bukkit.Material;

import fr.neatmonster.nocheatplus.utilities.map.BlockFlags;
import fr.neatmonster.nocheatplus.utilities.map.BlockProperties;

/**
 * Auxiliary methods for block initialization.
 * @author mc_dev
 *
 */
public class BlockInit {

    // TODO: Change to assert names only?, would be better with being able to feed MC names or map them as well, though.

    /**
     * Check for Material existence, throw RuntimeException if not.
     * @param id
     */
    public static void assertMaterialExists(int id) {
        if (BlockProperties.getMaterial(id) == null) {
            throw new RuntimeException("Material " + id + " does not exist.");
        }
    }

    /**
     * Check for material existence and naming (exact match).
     * @param id
     * @param name
     */
    public static void assertMaterialName(int id, String name) {
        Material mat = BlockProperties.getMaterial(id);
        if ( mat == null) {
            throw new RuntimeException("Material " + id + " does not exist.");
        }
        if (mat.name().equals(name)) {
            throw new RuntimeException("Name for Material " + id + " ('" + mat.name() + "') does not match '" + name + "'.");
        }
    }

    /**
     * Check for material existence and naming (parts must all be contained with ignored case).
     * @param id
     * @param parts
     */
    public static void assertMaterialNameMatch(int id, String... parts) {
        Material mat = BlockProperties.getMaterial(id);
        if ( mat == null) {
            throw new RuntimeException("Material " + id + " does not exist.");
        }
        String name = mat.name().toLowerCase();
        for (String part : parts) {
            if (name.indexOf(part.toLowerCase()) < 0) {
                throw new RuntimeException("Name for Material " + id + " ('" + mat.name() + "') should contain '" + part + "'.");
            }
        }
    }

    /**
     * Set block breaking properties same as the block of the given material.
     * @param newId
     * @param mat
     */
    public static void setPropsAs(int newId, Material mat) {
        BlockProperties.setBlockProps(newId, BlockProperties.getBlockProps(mat));
    }

    /**
     * Set block breaking properties same as the block of the given id.
     * @param newId
     * @param mat
     */
    public static void setPropsAs(int newId, int otherId) {
        BlockProperties.setBlockProps(newId, BlockProperties.getBlockProps(otherId));
    }

    /**
     * Set block breaking and shape properties same as the block of the given material.
     * @param newId
     * @param mat
     */
    public static void setAs(int newId, Material mat) {
        BlockFlags.setFlagsAs(newId, mat);
        setPropsAs(newId, mat);
    }

    /**
     * Set block breaking and shape properties same as the block of the given id.
     * @param newId
     * @param mat
     */
    public static void setAs(int newId, int otherId) {
        BlockFlags.setFlagsAs(newId, otherId);
        setPropsAs(newId, otherId);
    }

    /**
     * Set like air, plus instantly breakable.
     * @param newId
     */
    public static void setInstantAir(int newId) {
        BlockFlags.setFlagsAs(newId, Material.AIR);
        BlockProperties.setBlockProps(newId, BlockProperties.instantType);
    }

}
