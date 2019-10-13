/*
 * Copyright (C) 2019 SillyMoto authors
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
package de.beuth.master.classes;

import com.google.gson.annotations.SerializedName;

/**
 * <h1>Grant Class!</h1>
 * <p>
 * This class implements all fields of an Grant.
 * Grants are included in the API Key class.
 * It's an List of permissions per Key.
 *
 * @author  Sarah Kommorovski
 * @version 1.0
 * @since   2019-09-30
 */
public class Grant {
    @SerializedName("permission")
    private String permission;
    //@SerializedName("target")
    //private Target target;

    public Grant(String permission) {
        this.permission = permission;
        //this.target = target;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
