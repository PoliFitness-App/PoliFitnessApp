@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
)

package com.uca.polifitnessapp.ui.extras.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.uca.polifitnessapp.R
import com.uca.polifitnessapp.ui.navigation.components.BackButton
import com.uca.polifitnessapp.ui.theme.md_theme_light_outline
import com.uca.polifitnessapp.ui.theme.md_theme_light_scrim

@Composable
fun privacyPoliticsScreen(
    onBackPress: () -> Unit,
) {
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {

        item {
            BackButton(
                modifier = Modifier.padding(24.dp,16.dp,8.dp,8.dp),
                onBackPress
            )
            politicTittle()
            Spacer(modifier = Modifier.height(25.dp))
            politicsDescription()
            Spacer(modifier = Modifier.height(25.dp))

            interAndDefTittle()
            Spacer(modifier = Modifier.height(25.dp))
            interpretation()
            Spacer(modifier = Modifier.height(25.dp))
            definition()
            Spacer(modifier = Modifier.height(25.dp))

            infoUsageTittle()
            Spacer(modifier = Modifier.height(25.dp))
            personalInformation()
            Spacer(modifier = Modifier.height(25.dp))
            ussageData()
            Spacer(modifier = Modifier.height(25.dp))
            ussagePersonalData()
            Spacer(modifier = Modifier.height(25.dp))
            personalDataRetention()
            Spacer(modifier = Modifier.height(25.dp))
            deletePersonalData()
            Spacer(modifier = Modifier.height(25.dp))

            personalDataDivulgationTittle()
            Spacer(modifier = Modifier.height(25.dp))
            lawEnforcement()
            Spacer(modifier = Modifier.height(25.dp))
            otherRequirements()
            Spacer(modifier = Modifier.height(25.dp))

            personalDataSecurityTittle()
            Spacer(modifier = Modifier.height(25.dp))
            personalDataSecurity()
            Spacer(modifier = Modifier.height(25.dp))

            politicsChangeTittle()
            Spacer(modifier = Modifier.height(25.dp))
            politicsChange()
            Spacer(modifier = Modifier.height(25.dp))

            contactUsTittle()
            Spacer(modifier = Modifier.height(25.dp))
            contactUs()
            Spacer(modifier = Modifier.height(25.dp))
        }
    }
}

@Composable
fun politicTittle(){
    Text(modifier = Modifier
        .fillMaxWidth()
        .padding(35.dp, 0.dp),
        text = "Política de privacidad",
        style = TextStyle(fontWeight = FontWeight.Bold),
        fontSize = 20.sp,
    )
}

@Composable
fun politicsDescription() {
    Text(modifier = Modifier
        .fillMaxWidth()
        .padding(35.dp, 0.dp),
        text = "Última actualización: 20 de junio de 2023\n" +
                "\nEsta Política de privacidad describe Nuestras políticas y procedimientos sobre la recopilación, el uso y la divulgación de Su información cuando utiliza el Servicio y le informa sobre Sus derechos de privacidad y cómo la ley lo protege.\n" +
                "\nUsamos sus datos personales para proporcionar y mejorar el Servicio. Al usar el Servicio, acepta la recopilación y el uso de información de acuerdo con esta Política de privacidad. Esta Política de Privacidad ha sido creada con la ayuda del Generador de Políticas de Privacidad .",
        fontWeight = FontWeight.Light,
        fontSize = 12.sp,
        color = md_theme_light_scrim
    )
}

@Composable
fun interAndDefTittle(){
    Text(modifier = Modifier
        .fillMaxWidth()
        .padding(35.dp, 0.dp),
        text = "Interpretación y Definiciones",
        style = TextStyle(fontWeight = FontWeight.Bold),
        fontSize = 20.sp,
    )
}

@Composable
fun interpretation() {
    Text(modifier = Modifier
        .fillMaxWidth()
        .padding(35.dp, 0.dp),
        text = "Interpretación",
        style = TextStyle(fontWeight = FontWeight.Bold),
        fontSize = 17.sp,
    )

    Text(modifier = Modifier
        .fillMaxWidth()
        .padding(35.dp, 0.dp),
        text = "\nLas palabras cuya letra inicial está en mayúscula tienen significados definidos bajo las siguientes condiciones. Las siguientes definiciones tendrán el mismo significado independientemente de que aparezcan en singular o en plural.",
        fontWeight = FontWeight.Light,
        fontSize = 12.sp,
        color = md_theme_light_scrim
    )
}

@Composable
fun definition() {
    Text(modifier = Modifier
        .fillMaxWidth()
        .padding(35.dp, 0.dp),
        text = "Definiciones",
        style = TextStyle(fontWeight = FontWeight.Bold),
        fontSize = 17.sp,
    )

    Text(modifier = Modifier
        .fillMaxWidth()
        .padding(35.dp, 0.dp),
        text = "\nA los efectos de esta Política de Privacidad:\n" +
                "\n•\tCuenta significa una cuenta única creada para que Usted acceda a nuestro Servicio o partes de nuestro Servicio.\n" +
                "\n•\tAplicación se refiere a Centro Deportivo App, el programa de software proporcionado por la Compañía.\n" +
                "\n•\tLa Compañía (referida como \"la Compañía\", \"Nosotros\", \"Nos\" o \"Nuestro\" en este Acuerdo) se refiere a la Aplicación Centro Deportivo.\n" +
                "\n•\tPaís se refiere a: El Salvador\n" +
                "\n•\tDispositivo significa cualquier dispositivo que pueda acceder al Servicio, como una computadora, un teléfono celular o una tableta digital.\n" +
                "\n•\tLos datos personales son cualquier información que se relaciona con un individuo identificado o identificable.\n" +
                "\n•\tServicio se refiere a la Aplicación.\n" +
                "\n•\tProveedor de servicios significa cualquier persona física o jurídica que procesa los datos en nombre de la Compañía. Se refiere a empresas de terceros o personas empleadas por la Empresa para facilitar el Servicio, proporcionar el Servicio en nombre de la Empresa, realizar servicios relacionados con el Servicio o ayudar a la Empresa a analizar cómo se utiliza el Servicio.\n" +
                "\n•\tLos Datos de uso se refieren a los datos recopilados automáticamente, ya sea generados por el uso del Servicio o por la propia infraestructura del Servicio (por ejemplo, la duración de una visita a la página).\n" +
                "\n•\tUsted se refiere a la persona que accede o utiliza el Servicio, o la empresa u otra entidad legal en nombre de la cual dicha persona accede o utiliza el Servicio, según corresponda.",
        fontWeight = FontWeight.Light,
        fontSize = 12.sp,
        color = md_theme_light_scrim
    )
}

@Composable
fun infoUsageTittle(){
    Text(modifier = Modifier
        .fillMaxWidth()
        .padding(35.dp, 0.dp),
        text = "Recopilación y uso de sus datos personales",
        style = TextStyle(fontWeight = FontWeight.Bold),
        fontSize = 20.sp,
    )
}

@Composable
fun personalInformation() {
    Text(modifier = Modifier
        .fillMaxWidth()
        .padding(35.dp, 0.dp),
        text = "Información personal",
        style = TextStyle(fontWeight = FontWeight.Bold),
        fontSize = 17.sp,
    )

    Text(modifier = Modifier
        .fillMaxWidth()
        .padding(35.dp, 0.dp),
        text = "\nMientras usa Nuestro aplicación, podemos pedirle que nos proporcione cierta información de identificación personal que se puede usar para contactarlo o identificarlo. La información de identificación personal puede incluir, entre otros:\n" +
                "\n    • Dirección de correo electrónico\n" +
                "    • Usuario\n" +
                "    • Contraseña\n" +
                "    • Altura\n" +
                "    • Edad\n" +
                "    • Peso\n" +
                "    • Diámetro de cintura",
        fontWeight = FontWeight.Light,
        fontSize = 12.sp,
        color = md_theme_light_scrim
    )
}

@Composable
fun ussageData() {
    Text(modifier = Modifier
        .fillMaxWidth()
        .padding(35.dp, 0.dp),
        text = "Datos de uso",
        style = TextStyle(fontWeight = FontWeight.Bold),
        fontSize = 17.sp,
    )

    Text(modifier = Modifier
        .fillMaxWidth()
        .padding(35.dp, 0.dp),
        text = "\nLos Datos de uso se recopilan automáticamente cuando se utiliza el Servicio.\n" +
                "\nLos datos de uso pueden incluir información como la dirección del protocolo de Internet de su dispositivo (por ejemplo, la dirección IP); Cuando accede al Servicio a través de un dispositivo móvil, podemos recopilar cierta información automáticamente, que incluye, entre otros, el tipo de dispositivo móvil que utiliza, la identificación única de su dispositivo móvil, la dirección IP de su dispositivo móvil, su sistema operativo, el tipo de navegador de Internet móvil que utiliza, identificadores únicos de dispositivos y otros datos de diagnóstico.",
        fontWeight = FontWeight.Light,
        fontSize = 12.sp,
        color = md_theme_light_scrim
    )
}

@Composable
fun ussagePersonalData() {
    Text(modifier = Modifier
        .fillMaxWidth()
        .padding(35.dp, 0.dp),
        text = "Uso de sus datos personales",
        style = TextStyle(fontWeight = FontWeight.Bold),
        fontSize = 17.sp,
    )

    Text(modifier = Modifier
        .fillMaxWidth()
        .padding(35.dp, 0.dp),
        text = "\nLa aplicación puede utilizar los Datos Personales para los siguientes propósitos:\n" +
                "\n•\tPara proporcionar y mantener nuestro Servicio, incluso para monitorear el uso de nuestro Servicio.\n" +
                "\n•\tPara gestionar Su Cuenta: para gestionar Su registro como usuario del Servicio. Los Datos Personales que proporcione pueden darle acceso a diferentes funcionalidades del Servicio que están disponibles para Usted como usuario registrado.\n" +
                "\n•\tPara contactarlo: para contactarlo por correo electrónico, u otras formas equivalentes de comunicación electrónica, como notificaciones automáticas de una aplicación móvil sobre actualizaciones o comunicaciones informativas relacionadas con las funcionalidades, productos o servicios contratados, incluidas las actualizaciones de seguridad, cuando sea necesario o razonable para su implementación.\n" +
                "\n•\tPara proporcionarle noticias e información general sobre otros bienes, servicios y eventos que ofrecemos que son similares.\n" +
                "\n•\tPara gestionar Sus solicitudes: Para atender y gestionar Sus solicitudes hacia Nosotros.\n" +
                "\n•\tPara transferencias comerciales: podemos usar su información para evaluar o realizar una fusión, venta, reestructuración, reorganización, disolución u otra venta o transferencia de algunos o todos nuestros activos, ya sea como una empresa en marcha o como parte de una quiebra, liquidación, o procedimiento similar, en el que los Datos personales que tenemos sobre los usuarios de nuestro Servicio se encuentran entre los activos transferidos.\n" +
                "\n•\tPara otros fines: podemos utilizar su información para otros fines, como el análisis de datos, la identificación de tendencias de uso, la determinación de la eficacia de nuestras campañas promocionales y para evaluar y mejorar nuestro Servicio, productos, servicios, marketing y su experiencia.\n" +
                "\nPodemos compartir su información personal en las siguientes situaciones:\n" +
                "\n•\tCon Proveedores de Servicios: Podemos compartir Su información personal con Proveedores de Servicios para monitorear y analizar el uso de nuestro Servicio, para contactarlo.\n" +
                "\n•\tPara transferencias comerciales: Podemos compartir o transferir Su información personal en relación con, o durante las negociaciones de, cualquier fusión, venta de activos de la Compañía, financiamiento o adquisición de todo o una parte de Nuestro negocio a otra compañía.\n" +
                "\n•\tCon afiliados: podemos compartir su información con nuestros afiliados, en cuyo caso exigiremos a esos afiliados que respeten esta Política de privacidad. Los afiliados incluyen nuestra empresa matriz y cualquier otra subsidiaria, socios de empresas conjuntas u otras empresas que controlamos o que están bajo control común con nosotros.\n" +
                "\n•\tCon socios comerciales: Podemos compartir Su información con Nuestros socios comerciales para ofrecerle ciertos productos, servicios o promociones.\n" +
                "\n•\tCon otros usuarios: cuando comparte información personal o interactúa en las áreas públicas con otros usuarios, dicha información puede ser vista por todos los usuarios y puede distribuirse públicamente al exterior.\n" +
                "\n•\tCon su consentimiento: podemos divulgar su información personal para cualquier otro propósito con su consentimiento.",
        fontWeight = FontWeight.Light,
        fontSize = 12.sp,
        color = md_theme_light_scrim
    )
}

@Composable
fun personalDataRetention() {
    Text(modifier = Modifier
        .fillMaxWidth()
        .padding(35.dp, 0.dp),
        text = "Retención de sus datos personales",
        style = TextStyle(fontWeight = FontWeight.Bold),
        fontSize = 17.sp,
    )

    Text(modifier = Modifier
        .fillMaxWidth()
        .padding(35.dp, 0.dp),
        text = "\nLa aplicación conservará sus Datos personales solo durante el tiempo que sea necesario para los fines establecidos en esta Política de privacidad. Conservaremos y utilizaremos sus datos personales en la medida necesaria para cumplir con nuestras obligaciones legales (por ejemplo, si estamos obligados a conservar sus datos para cumplir con las leyes aplicables), resolver disputas y hacer cumplir nuestros acuerdos y políticas legales.\n" +
                "\nLa aplicación también conservará los Datos de uso para fines de análisis interno. Los Datos de uso generalmente se retienen por un período de tiempo más corto, excepto cuando estos datos se utilizan para fortalecer la seguridad o mejorar la funcionalidad de Nuestro Servicio, o cuando estamos legalmente obligados a retener estos datos por períodos de tiempo más largos.",
        fontWeight = FontWeight.Light,
        fontSize = 12.sp,
        color = md_theme_light_scrim
    )
}

@Composable
fun deletePersonalData() {
    Text(modifier = Modifier
        .fillMaxWidth()
        .padding(35.dp, 0.dp),
        text = "Eliminar sus datos personales",
        style = TextStyle(fontWeight = FontWeight.Bold),
        fontSize = 17.sp,
    )

    Text(modifier = Modifier
        .fillMaxWidth()
        .padding(35.dp, 0.dp),
        text = "\nTiene derecho a eliminar o solicitar que lo ayudemos a eliminar los Datos personales que hemos recopilado sobre usted.\n" +
                "\nNuestro Servicio puede darle la capacidad de eliminar cierta información sobre Usted dentro del Servicio.\n" +
                "\nPuede actualizar, modificar o eliminar su información en cualquier momento iniciando sesión en su cuenta, si tiene una, y visitando la sección de configuración de la cuenta que le permite administrar su información personal. También puede comunicarse con nosotros para solicitar acceso, corregir o eliminar cualquier información personal que nos haya proporcionado.\n" +
                "\nSin embargo, tenga en cuenta que es posible que necesitemos conservar cierta información cuando tengamos una obligación legal o una base legal para hacerlo.",
        fontWeight = FontWeight.Light,
        fontSize = 12.sp,
        color = md_theme_light_scrim
    )
}

@Composable
fun personalDataDivulgationTittle(){
    Text(modifier = Modifier
        .fillMaxWidth()
        .padding(35.dp, 0.dp),
        text = "Divulgación de sus datos personales",
        style = TextStyle(fontWeight = FontWeight.Bold),
        fontSize = 20.sp,
    )
}

@Composable
fun lawEnforcement() {
    Text(modifier = Modifier
        .fillMaxWidth()
        .padding(35.dp, 0.dp),
        text = "Cumplimiento de la ley",
        style = TextStyle(fontWeight = FontWeight.Bold),
        fontSize = 17.sp,
    )

    Text(modifier = Modifier
        .fillMaxWidth()
        .padding(35.dp, 0.dp),
        text = "En determinadas circunstancias, es posible que se le solicite a la Compañía que divulgue sus Datos personales si así lo exige la ley o en respuesta a solicitudes válidas de las autoridades públicas (por ejemplo, un tribunal o una agencia gubernamental).",
        fontWeight = FontWeight.Light,
        fontSize = 12.sp,
        color = md_theme_light_scrim
    )
}

@Composable
fun otherRequirements() {
    Text(modifier = Modifier
        .fillMaxWidth()
        .padding(35.dp, 0.dp),
        text = "Otros requisitos legales",
        style = TextStyle(fontWeight = FontWeight.Bold),
        fontSize = 17.sp,
    )

    Text(modifier = Modifier
        .fillMaxWidth()
        .padding(35.dp, 0.dp),
        text = "La Compañía puede divulgar sus datos personales de buena fe cuando considere que esta acción es necesaria para lo siguiente:\n" +
                "\n•\tCumplir con una obligación legal\n" +
                "\n•\tProteger y defender los derechos o propiedad de la Compañía\n" +
                "\n•\tPrevenir o investigar posibles irregularidades en relación con el Servicio\n" +
                "\n•\tProteger la seguridad personal de los Usuarios del Servicio o del público\n" +
                "\n•\tProtéjase contra la responsabilidad legal",
        fontWeight = FontWeight.Light,
        fontSize = 12.sp,
        color = md_theme_light_scrim
    )
}

@Composable
fun personalDataSecurityTittle(){
    Text(modifier = Modifier
        .fillMaxWidth()
        .padding(35.dp, 0.dp),
        text = "Seguridad de sus datos personales",
        style = TextStyle(fontWeight = FontWeight.Bold),
        fontSize = 20.sp,
    )
}

@Composable
fun personalDataSecurity() {
    Text(modifier = Modifier
        .fillMaxWidth()
        .padding(35.dp, 0.dp),
        text = "La seguridad de sus datos personales es importante para nosotros, pero recuerde que ningún método de transmisión por Internet o método de almacenamiento electrónico es 100 % seguro. Si bien nos esforzamos por utilizar medios comercialmente aceptables para proteger sus datos personales, no podemos garantizar su seguridad absoluta.",
        fontWeight = FontWeight.Light,
        fontSize = 12.sp,
        color = md_theme_light_scrim
    )
}

@Composable
fun politicsChangeTittle(){
    Text(modifier = Modifier
        .fillMaxWidth()
        .padding(35.dp, 0.dp),
        text = "Cambios a estas políticas de privacidad",
        style = TextStyle(fontWeight = FontWeight.Bold),
        fontSize = 20.sp,
    )
}

@Composable
fun politicsChange() {
    Text(modifier = Modifier
        .fillMaxWidth()
        .padding(35.dp, 0.dp),
        text = "Es posible que actualicemos nuestra Política de privacidad de vez en cuando. Le notificaremos cualquier cambio publicando la nueva Política de privacidad en esta página.\n" +
                "\nLe informaremos por correo electrónico y/o un aviso destacado en Nuestro Servicio, antes de que el cambio entre en vigencia y actualizaremos la fecha de \"Última actualización\" en la parte superior de esta Política de privacidad.\n" +
                "\nSe le recomienda revisar esta Política de Privacidad periódicamente para cualquier cambio. Los cambios a esta Política de privacidad son efectivos cuando se publiquen.",
        fontWeight = FontWeight.Light,
        fontSize = 12.sp,
        color = md_theme_light_scrim
    )
}

@Composable
fun contactUsTittle(){
    Text(modifier = Modifier
        .fillMaxWidth()
        .padding(35.dp, 0.dp),
        text = "Contáctenos",
        style = TextStyle(fontWeight = FontWeight.Bold),
        fontSize = 20.sp,
    )
}

@Composable
fun contactUs() {
    Text(modifier = Modifier
        .fillMaxWidth()
        .padding(35.dp, 0.dp),
        text = "Si tiene alguna pregunta sobre esta Política de privacidad, puede contactarnos:\n" +
                "\n•\tPor correo electrónico: politnessuca@gmail.com\n" +
                "\n•\tAl visitar esta página en nuestro sitio web: https://www.deportivouca.tech",
        fontWeight = FontWeight.Light,
        fontSize = 12.sp,
        color = md_theme_light_scrim
    )
}



