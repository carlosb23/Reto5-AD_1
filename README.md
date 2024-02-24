
# Api Rest Spring Boot

Imagina que estás trabajando en el desarrollo de una aplicación móvil para el turismo, y necesitas crear una API REST que permita realizar operaciones básicas de lectura y escritura sobre distintos recursosrelacionados con el turismoque se almacenan en una base de datosen nuestro propio servidor.Queremos realizar una primera versión de la API REST en local para realizar pruebas desde el emulador.





## Authors

- [@carlosb23](https://github.com/carlosb23/Reto5-AD_1.git)


# API Reference

## Tiempo Endpoints

### Endpoint: Add Tiempo

- **URL:** `/tiempo/add`
- **Método:** `POST`
- **Descripción:** Agrega un nuevo registro de tiempo a la base de datos sin seguridad.

| Parámetro | Tipo     | Descripción |
| :-------- | :------- | :---------- |
| `api_key` | `string` | **Requerido**. Tu clave de API |

### Endpoint: Get All Tiempos

- **URL:** `/tiempo/all`
- **Método:** `GET`
- **Descripción:** Obtiene todos los objetos de tiempo de la base de datos.

| Parámetro | Tipo     | Descripción |
| :-------- | :------- | :---------- |
| `api_key` | `string` | **Requerido**. Tu clave de API |

### Endpoint: Get Tiempo by ID

- **URL:** `/tiempo/{id}`
- **Método:** `GET`
- **Descripción:** Obtiene un único registro de tiempo por su ID.

| Parámetro | Tipo     | Descripción |
| :-------- | :------- | :---------- |
| `id`      | `string` | **Requerido**. ID del elemento a recuperar. |

### Endpoint: Find Tiempos by Temperature

- **URL:** `/tiempo/temperatura/{temperatura}`
- **Método:** `GET`
- **Descripción:** Encuentra todos los registros de tiempo con una temperatura específica.

| Parámetro     | Tipo     | Descripción |
| :------------ | :------- | :---------- |
| `temperatura` | `number` | **Requerido**. Temperatura a buscar. |

### Endpoint: Count Cities with Temperature

- **URL:** `/tiempo/count/{temperatura}`
- **Método:** `GET`
- **Descripción:** Obtiene el número de ciudades con una temperatura superior a la proporcionada.

| Parámetro     | Tipo     | Descripción |
| :------------ | :------- | :---------- |
| `temperatura` | `number` | **Requerido**. Temperatura de referencia. |

### Endpoint: Get Average Temperature

- **URL:** `/tiempo/promedio`
- **Método:** `GET`
- **Descripción:** Obtiene la temperatura promedio de todos los registros de tiempo.

### Endpoint: Update Tiempo by ID

- **URL:** `/tiempo/actualizar/{id}`
- **Método:** `PUT`
- **Descripción:** Actualiza el registro de tiempo con el ID seleccionado para cambiar sus datos.

| Parámetro | Tipo     | Descripción |
| :-------- | :------- | :---------- |
| `id`      | `string` | **Requerido**. ID del elemento a actualizar. |

## Ciudad Endpoints

### Endpoint: Get Tiempo by City Name

- **URL:** `/ciudad/{nombre}`
- **Método:** `GET`
- **Descripción:** Obtiene un registro de tiempo por el nombre de la ciudad.

| Parámetro | Tipo     | Descripción |
| :-------- | :------- | :---------- |
| `nombre`  | `string` | **Requerido**. Nombre de la ciudad. |

### Endpoint: Get All Tiempos for Ciudad Controller

- **URL:** `/ciudad/all`
- **Método:** `GET`
- **Descripción:** Obtiene todos los registros de tiempo para el controlador de la ciudad.

### Endpoint: Get Tiempo by ID for Ciudad

- **URL:** `/ciudad/{id}`
- **Método:** `GET`
- **Descripción:** Obtiene un único registro de tiempo por su ID para la ciudad.

| Parámetro | Tipo     | Descripción |
| :-------- | :------- | :---------- |
| `id`      | `string` | **Requerido**. ID del elemento a recuperar. |

### Endpoint: Add Ciudad with Security

- **URL:** `/ciudad/addsecurity`
- **Método:** `POST`
- **Descripción:** Agrega un nuevo registro de tiempo a la base de datos con seguridad.

| Parámetro | Tipo     | Descripción                |
| :-------- | :------- | :------------------------- |
| `api_key` | `string` | **Requerido**. Tu clave de API |
| `data`    | `object` | **Requerido**. Objeto que contiene los datos del nuevo registro de tiempo. |

### Endpoint: Update Ciudad with Security by ID

- **URL:** `/ciudad/actualizarsecurity/{id}`
- **Método:** `PUT`
- **Descripción:** Actualiza un registro en la base de datos con seguridad.

| Parámetro | Tipo     | Descripción |
| :-------- | :------- | :---------- |
| `id`      | `string` | **Requerido**. ID del elemento a actualizar. |

### Endpoint: Delete Ciudad by ID with Security

- **URL:** `/ciudad/eliminar/{id}`
- **Método:** `DELETE`
- **Descripción:** Elimina un registro de tiempo de la base de datos con seguridad.

| Parámetro | Tipo     | Descripción |
| :-------- | :------- | :---------- |
| `id`      | `string` | **Requerido**. ID del elemento a eliminar. |




