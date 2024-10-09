package org.launchcode.weapon_inventory.controllers;

import org.launchcode.weapon_inventory.Weapon;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/weapons")
public class WeaponController {

    private List<Weapon> weaponInventory = new ArrayList<>();

    @GetMapping
    public List<Weapon> getAllWeapons() {
        return weaponInventory;
    }

    @PostMapping
    public Weapon addWeapon(@RequestBody Weapon weapon) {
        weapon.setId((long) (weaponInventory.size() + 1)); // Simple auto-increment ID
        weaponInventory.add(weapon);
        return weapon;
    }

    // Full replacement via PUT
    @PutMapping("/{id}")
    public Weapon updateWeapon(@PathVariable Long id, @RequestBody Weapon updatedWeapon) {
        for (Weapon weapon : weaponInventory) {
            if (weapon.getId().equals(id)) {
                weapon.setName(updatedWeapon.getName());
                weapon.setType(updatedWeapon.getType());
                weapon.setDamage(updatedWeapon.getDamage());
                weapon.setRange(updatedWeapon.getRange());
                return weapon;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Weapon not found");
    }

    // Partial update via PATCH
    @PatchMapping("/{id}")
    public Weapon patchWeapon(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        for (Weapon weapon : weaponInventory) {
            if (weapon.getId().equals(id)) {
                // Update each field if it's present in the patch request and handle type conversion
                if (updates.containsKey("name")) {
                    weapon.setName((String) updates.get("name"));
                }
                if (updates.containsKey("type")) {
                    weapon.setType((String) updates.get("type"));
                }
                if (updates.containsKey("damage")) {
                    // Convert the incoming value to an integer
                    weapon.setDamage(Integer.parseInt(updates.get("damage").toString()));
                }
                if (updates.containsKey("range")) {
                    weapon.setRange((String) updates.get("range"));
                }
                return weapon;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Weapon not found");
    }


    @DeleteMapping("/{id}")
    public String deleteWeapon(@PathVariable Long id) {
        weaponInventory.removeIf(weapon -> weapon.getId().equals(id));
        return "Weapon removed from inventory";
    }
}
