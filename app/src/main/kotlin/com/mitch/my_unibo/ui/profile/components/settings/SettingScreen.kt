package com.mitch.my_unibo.ui.profile.components.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.mitch.my_unibo.ui.theme.custom.padding
import com.mitch.my_unibo.ui.util.SecondaryScreen

@Composable
fun SettingScreen(
    idSetting: Int?,
    navController: NavHostController,
    systemController: SystemUiController
) {
    if (idSetting == null) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Nessuna impostazione trovata")
        }
    } else {
        val settingName = when (idSetting) {
            1 -> "Aule"
            2 -> "Biblioteche"
            3 -> "Contatti"
            else -> ""
        }

        SecondaryScreen(
            title = settingName,
            navController = navController,
            systemController = systemController
        ) {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                when (idSetting) {
                    1 -> RoomSetting()
                    2 -> LibrarySetting()
                    3 -> ContactSetting()
                    else -> throw NotImplementedError()
                }
            }
        }
    }
}


@Composable
private fun RoomSetting() {

}

@Composable
private fun LibrarySetting() {

}

@Composable
private fun ContactSetting() {
    Column(
        modifier = Modifier.padding(padding.medium),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "SERVIZIO DIDATTICO",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Row {
            Text(
                text = """Orario delle lezioni, appelli d'esame, tirocini e tesi di laurea. Presta
                assistenza alla compilaione dei piani di studio e riconoscimento crediti in casi
                di passaggio e/o trasferimento fungendo da raccordo tra docenti, studenti e
                Segreteria Studenti.""".replace("\\s+".toRegex(), " ")
            )
        }
        Row {
            Text(text = "Via dell'Università, 50 - 47521 Cesena(FC)")
        }
        Row {
            Text(text = "campuscesena.didattica.isa@unibo.it")
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Row {
                Text(text = "Tel:")
            }
            Row {
                Text(text = "+39.0547-338300")
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Row {
                Text(text = "Fax:")
            }
            Row {
                Text(text = "+39.051 2086293")
            }
        }
        Row {
            Text(
                text = """
                   Gli uffici svolgono il loro servizio ed il ricevimento dell'utenza via e-mail,
                   telefono e Teams dal lunedì al venerdì dalle 9:30 alle 12:00 e dalle 14:30 alle 15:30.
                   Scrivi una e-mail per informazioni o per un collegamento diretto tramite l'app
                   Teams. Entro 2 giorni lavorativi riceverai una e-mail con data, ora e link a cui
                   collegarti dopo aver scaricato l'app.
                """.replace("\\s+".toRegex(), " ")
            )
        }
    }
    Divider()
    Column(
        modifier = Modifier.padding(padding.medium),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "SERVIZIO RELAZIONI INTERNAZIONALI",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Row {
            Text(
                text = """Programmi di mobilità internazionale, opportunità di studio, tirocinio
                    e formazione all'estero, learning agreement, accoglienza studenti stranieri in 
                    scambio e studenti internazionali.
                """.replace("\\s+".toRegex(), " ")
            )
        }
        Row {
            Text(text = "Via Montalti, 69 - 47521 Cesena(FC)")
        }
        Row {
            Text(text = "campuscesena.uri@unibo.it")
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Row {
                Text(text = "Tel:")
            }
            Row {
                Text(text = "+39.0547-339006")
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Row {
                Text(text = "Fax:")
            }
            Row {
                Text(text = "+39.051 2086305")
            }
        }
        Row {
            Text(text = "https://www.unibo.it/it/campus-cesena/servizi-di-campus/ufficio-relazioni-internazionali-uri")
        }
    }
    Divider()
    Column(
        modifier = Modifier.padding(padding.medium),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "UFFICIO MOBILITÀ INTERNAZIONALE SCIENZE",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Row {
            Text(
                text = "Mobilità internazionale"
            )
        }
        Row {
            Text(text = "Via Filippo Re, 8 - 40126 Bologna(BO)")
        }
        Row {
            Text(text = "scienze.mobint@unibo.it")
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Row {
                Text(text = "Tel:")
            }
            Row {
                Text(text = "051 2091262")
            }
        }
        Row {
            Text(
                text = """
                    Orario di ricevimento e sportello telefonico lun, mar, mer e ven dalle 9:00 alle
                    11:15 gio dalle 14:30 alle 15:30
                """.replace("\\s+".toRegex(), " ")
            )
        }
    }
    Divider()
    Column(
        modifier = Modifier.padding(padding.medium),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "TUTOR PER L'INTERNAZIONALIZZAZIONE",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Row {
            Text(
                text = """
                    Orientamento e assistenza per gli studenti in ingresso e in uscita nell'ambito
                    di programmi di scambio internazionali.
                """.replace("\\s+".toRegex(), " ")
            )
        }
        Row {
            Text(text = "GIULIA BRUGNATTI")
        }
        Row {
            Text(text = "Via dell'Università, 50 - Cesena(FC)")
        }
        Row {
            Text(text = "campuscesena.internazionalizzazioneingegneria@unibo.it")
        }
        Row {
            Text(text = "Il tutor riceve su appuntamento da fissare tramite e-mail")
        }
    }
    Divider()
    Column(
        modifier = Modifier.padding(padding.medium),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "SERVIZIO TIROCINI",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Row {
            Text(
                text = """
                    Organizzazione e gestione di attività di stage e tirocinio curriculare e post-lauream,
                    incluso il tirocinio a carattere professionalizzante.
                """.replace("\\s+".toRegex(), " ")
            )
        }
        Row {
            Text(text = "Via Montalti, 69 - 47521 Cesena(FC)")
        }
        Row {
            Text(text = "campuscesena.tirocini@unibo.it")
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Row {
                Text(text = "Tel:")
            }
            Row {
                Text(text = "+39.0547-338914")
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Row {
                Text(text = "Fax:")
            }
            Row {
                Text(text = "+39.051 2086305")
            }
        }
        Row {
            Text(text = "https://www.unibo.it/it/campus-cesena/servizi-di-campus/sertirelint")
        }
    }
}
