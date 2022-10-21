package com.example.firstexampleapp.model.recipe.repository

import com.example.firstexampleapp.R
import com.example.firstexampleapp.model.recipe.Level
import com.example.firstexampleapp.model.recipe.RecipeState

val listRecipe = listOf(
    RecipeState(
        idRecipe = 1,
        title = "Arroz con Leche",
        photo = R.mipmap.arroz_con_leche,
        mix = listOf(
            "100 grs de arroz intregral",
            "550 ml de leche",
            "1 rama de canela",
            "1/4 de limón",
            "Canela en polvo para decorar",
            "Unas cuantas gotas de escencia de vainilla (opcional)"
        ),
        cookingTime = "50 min",
        quantity = "4 personas",
        description =
        "1- Con la ayuda de un colador, limpiar bien el arroz." +
                "\n2- En una olla, poner agua a calentar i, cuando esté a punto de hervir, añadir el resto de los ingredientes (excepto la canela en polvo)." +
                "\n3- Cocinar a fuego lento 35-40 minutos, removiendo constantemente para que no se enganche." +
                "\n4- Reservar en la nevera un mínimo de 1-2 h antes de consumir." +
                "\n\nFuente:" +
                "\nUn Estiu Saludable: Receptes per un estiu amb salut del Colegio de Dietistas-Nutricionistas de Cataluña"
    ),
    RecipeState(
        idRecipe = 2,
        title = "Risotto con tomates asados y cebada",
        photo = R.mipmap.risotto_cebada,
        difficulty = Level.Medium,
        mix = listOf(
            "10 tomates Roma grandes",
            "2 cucharada de aceite de oliva extra virgen",
            "1/2 cucharada de pimienta molida y sal",
            "2 chalotas picadas",
            "2 tazas de cebada perlada",
            "3 cucharadas de albahaca fresca picada, y hojas enteras para guarnición",
            "3 cucharadas de perejil de hoja plana picado",
            "1 1/2 cucharadas de tomillo fresco picado",
            "1/2 taza de queso parmesano rallado"
        ),
        cookingTime = "60 min",
        quantity = "8 personas",
        description =
        "Calienta el horno a 190 °C. Coloca los tomates en una bandeja de horno antiadherente. Rocía con 1 cucharada de aceite de oliva y espolvorea con 1/4 de cucharadita de sal y 1/4 de cucharadita de pimienta. Revuelve suavemente para mezclar. " +
                "\nAsa los tomates hasta que estén blandos y comiencen a dorarse, entre 25 y 30 minutos. Reserva 16 gajos de tomates para usar como guarnición.\n" +
                "\n" +
                "En una cacerola, combina el caldo de verduras con el agua y pon a hervir a fuego alto. Reduce el calor, y cocina a fuego lento.\n" +
                "\n" +
                "En una cacerola grande, calienta la cucharada de aceite de oliva restante a fuego medio. Agrega las chalotas picadas y sofríe durante 2 a 3 minutos hasta que queden blandas y transparentes. Añade la cebada y cocina, revolviendo, durante 1 minuto. " +
                "\n\nAgrega 1/2 taza de la mezcla de caldo y cocina hasta que el líquido se absorba por completo, revolviendo de vez en cuando. Continúa agregando la mezcla de caldo de a media taza, cocinando cada vez hasta que el líquido se absorba antes de añadir más, hasta que la cebada esté tierna, unos 45 a 50 minutos en total. " +
                "\n\nRetira del calor e incorpora los tomates, la albahaca picada, el perejil, el tomillo y el queso rallado. Añade la 1/4 cucharadita de sal y la 1/4 cucharadita de pimienta restantes, y revuelve para mezclar.\n" +
                "\n" +
                "Divide el risotto en boles individuales poco profundos, calentados. Decora con los gajos de tomate asados reservados y las hojas de albahaca enteras. Con un pelador de verduras, corta una espiral o dos de queso parmesano para poner en la superficie de cada porción" +
                "\n\nFuente:" +
                "\nThe New Mayo Clinic Cookbook; mayoclinic.org" +
                "\nImagen: mondaydreams.com",
        nutritionInfo = listOf("287", "7g", "11g", "9g", "45g")
    ),
    RecipeState(
        idRecipe = 3,
        title = "Salmón asado al estilo mediterráneo",
        photo = R.mipmap.salmon_asado,
        mix = listOf(
            "4 cucharadas de albahaca fresca picada",
            "1 cucharada de perejil fresco picado",
            "1 cucharada de ajo picado",
            "2 cucharadas de jugo de limón",
            "4 filetes de salmón de 5 onzas (140 g) cada uno",
            "Pimienta negra machacada, a gusto",
            "4 aceitunas verdes picadas",
            "4 rojas finas de limón"
        ),
        cookingTime = "30 min",
        quantity = "4 personas",
        description =
        "Prepara el fuego en un asador de carbón o calienta una parrilla a gas o una asadera. Lejos de la fuente de calor, rocía ligeramente la parrilla o asadera con aceite en aerosol. Coloca la parrilla a una distancia de entre 4 y 6 pulgadas (de 10 a 15 cm) de la fuente de calor.\n" +
                "\n" +
                "En un bol pequeño, mezcla la albahaca, el perejil, el ajo picado y el jugo de limón. Rocía el pescado con aceite en aerosol. Espolvorea pimienta negra. Cubre cada filete con igual cantidad de la mezcla de hierbas y ajo. Coloca el pescado con las hierbas hacia abajo en la parrilla. Cocínalo a la parrilla a fuego alto. " +
                "\n\nCuando los bordes estén blancos, después de 3 o 4 minutos, voltea el pescado y colócalo sobre papel de aluminio. Lleva el pescado a una parte más fría de la parrilla o reduce el fuego. " +
                "\n\nCocina el pescado hasta que se vea totalmente opaco al introducir la punta de un cuchillo y un termómetro de lectura instantánea, puesto en la parte más gruesa del pescado, marque 145 °F (65 °C) (unos 4 minutos más).\n" +
                "\n" +
                "Retira el salmón y colócalo en platos calientes. Decóralo con aceitunas verdes y rodajas de limón." +
                "\n\nFuente:" +
                "\nmayoclinic.org" +
                "\nImagen: libroderecetas.com",
        nutritionInfo = listOf("214", "10g", "18g", "1g", "3g")
    ),
    RecipeState(
        idRecipe = 4,
        title = "Tallarines con espinaca, garbanzos y pasas",
        photo = R.mipmap.pasta_garbanzos,
        mix = listOf(
            "8 onzas (225 g) (3 tazas aproximadamente) de pasta seca en forma de moño",
            "2 cucharadas de aceite de oliva",
            "4 dientes de ajo triturados",
            "La mitad de una lata de garbanzos de 19 onzas (540 g) enjuagados y escurridos",
            "1/2 taza de caldo de pollo sin sal",
            "1/2 taza de pasas rubias",
            "4 tazas de espinaca fresca picada",
            "2 cucharadas de queso parmesano rallado",
            "Granos de pimienta negra machacada a gusto"
        ),
        cookingTime = "35 min",
        quantity = "6 personas",
        description =
        "Llena 3/4 de una olla grande con agua y ponla a hervir. Coloca la pasta y cocina hasta que esté al dente (tierna), de 10 a 12 minutos o según las instrucciones del paquete. Cuela la pasta por completo.\n" +
                "\n" +
                "En una sartén grande, calienta el aceite de oliva y el ajo a fuego medio. Agrega los garbanzos y el caldo de verduras. Revuelve hasta que se caliente bien. Agrega las pasas y la espinaca. Calienta hasta que la espinaca se ablande, durante aproximadamente 3 minutos. No lo cocines demasiado.\n" +
                "\n" +
                "Divide la pasta entre los platos. Cubre cada porción con 1/6 de salsa, 1 cucharadita de queso parmesano y granos de pimienta a gusto. Sirve de inmediato." +
                "\n\nFuente:" +
                "\nmayoclinic.org" +
                "Image: mx.hola.com",
        nutritionInfo = listOf("283", "7g", "11g", "6g", "44g")
    ),
    RecipeState(
        idRecipe = 5,
        title = "Estofado toscano de frijoles blancos",
        photo = R.mipmap.estofado_toscana,
        difficulty = Level.Senior,
        mix = listOf(
            "3 cucharadas de aceite de oliva extra virgen",
            "2 dientes de ajo cortados en cuartos",
            "1 rebanada de pan integral cortado en cubos de 1.30 cm",
            "2 tazas de cannellini secos u otros frijoles blancos, seleccionados y enjuagados, remojados durante la noche, y escurridos",
            "6 tazas de agua",
            "1/2 cucharadita de sal",
            "1 hoja de laurel",
            "1 taza de cebolla amarilla, picada grande",
            "3 zanahorias, peladas y picadas grande",
            "6 dientes de ajo picados",
            "1/4 cucharadita de pimienta negra recién molida",
            "1 cucharada de romero picado, y 6 ramitas",
            "1 1/2 tazas de caldo de verduras"
        ),
        cookingTime = "100 min",
        quantity = "6 personas",
        description =
        "Para hacer los croutones, calienta el aceite de oliva a fuego medio en una sartén grande. Agrega el ajo y sofríe durante 1 minuto. Retira del fuego y deja reposar durante 10 minutos para que el sabor del ajo penetre en el aceite. Retira los trozos de ajo y deséchelos. Vuelva a poner la sartén a fuego medio. Agrega los cubos de pan y sofríe de 3 a 5 minutos, revolviendo con frecuencia, hasta que se doren ligeramente. Pasa la preparación a un bol pequeño y déjala a un lado.\n" +
                "\n" +
                "Para hacer la sopa, combina en una olla los frijoles blancos, el agua, 1/4 cucharadita de sal y la hoja de laurel. Pon a hervir a fuego fuerte. Baja el fuego, cubre parcialmente y cocina a fuego lento entre 60 y 75 minutos, hasta que los frijoles estén tiernos. Escurre los frijoles, reservando 1/2 taza del líquido de cocción. Desecha la hoja de laurel. Coloca los frijoles cocidos en un bol grande y guarda la olla para usarla después.\n" +
                "\n" +
                "En un bol pequeño, mezcla el líquido de cocción reservado y 1/2 taza de frijoles cocidos. Písalos con un tenedor hasta formar una pasta. Combina la pasta de frijoles con los frijoles cocidos.\n" +
                "\n" +
                "Vuelve la olla a la hornalla de la estufa y agrega el aceite de oliva. Calienta a fuego medio-alto. Incorpora la cebolla y las zanahorias, y sofríe de 6 a 7 minutos hasta que las zanahorias estén tiernas, pero crujientes. Incorpora el ajo y cocina durante 1 minuto aproximadamente hasta que se ablande. Incorpora la 1/4 cucharadita de sal restante, la pimienta, el romero picado, la mezcla de frijoles y el caldo. Déjalo hervir y luego baja el fuego y cocina a fuego lento durante aproximadamente 5 minutos, hasta que el estofado se cocine por completo.\n" +
                "\n" +
                "Sirve el estofado con un cucharón en boles calientes y pon los croutones sobre el estofado. Decora cada bol con una ramita de romero y sirve inmediatamente." +
                "\n\nFuente:" +
                "\nmayoclinic.org",
        nutritionInfo = listOf("307", "7g", "16g", "11g", "45g")
    )
)