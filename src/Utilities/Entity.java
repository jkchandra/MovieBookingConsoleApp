package Utilities;

import java.io.Serializable;
import java.util.UUID;

/**
 *
 * @author Soggeh
 */
public abstract class Entity implements Serializable {
    /**
     * Unique ID of the entity
     */
    protected UUID uuid;

    /**
     * Create an entity with a unique number generated using UUID
     */
    public Entity() {
        this.uuid = UUID.randomUUID();
    }
    
     /**
     * Compare between entities using their UUID
     * @param object The new object to compare to
     * @return True of the UUID of both objects are the same
     */
    @Override
    public boolean equals(Object object) {
        if ((null == object) || !(object instanceof Entity))
            return false;
        Entity entity = (Entity) object;
        return entity.uuid == uuid;
    }

    /**
     * Get hash code value for the entity, generated using the UUID
     * @return a hash code value
     */
    @Override
    public int hashCode() {
        return uuid.hashCode();
    }
    
    /**
     * Return the entity's UUID
     * @param entity The entity to obtain the UUID from
     * @return This entity's UUID
     */
    public static UUID getUUID(Entity entity) {
        return entity.uuid;
    }
    
    /**
     * Return the entity's UUID
     * @param entity The entity to obtain the UUID from
     * @return This entity's UUID in the string format
     */
    public static String getUUIDString(Entity entity) {
        return entity.uuid.toString();
    }
    
    /**
     * Change the entity's  UUID
     * @param entity The entity to set the UUID
     * @param uuid The new UUID to replace the entity's UUID with
     */
    public void setId(Entity entity, UUID uuid) {
        entity.uuid = uuid;
    }
}
