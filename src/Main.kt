import java.io.File

const val BG_RED = "\u001B[41m"
const val RESET = "\u001B[0m"
const val BG_GREEN = "\u001B[42m"
const val BG_YELLOW = "\u001B[43m"
const val BLACK = "\u001B[30m"

fun main() {
    var control = 0
    while(control != 3) {
        println("Escoge modo:")
        println("1. Jugar")
        println("2. Partida anterior")
        println("3. Salir")
        print("opcion: ")
        control = readln().toInt()
        println("")
        when (control) {
            1 -> juego(5, 4)
            2 -> partidav()
            3 -> println("Adios !")
            else ->  println("${BG_RED}Numero no valido${RESET}")
        }
    }
}

fun juego(intentos: Int, longitud: Int) {

    val numjug = 0..9999
    var intent = intentos
    var intentog = 1
    var numgana = numjug.random().toString()
    var aciertos = 0
    var aprox = 0

    if(numgana.length < longitud) {
        while (numgana.length != longitud) {
            numgana = "0" + numgana
        }
    }
    File("partidas.txt").writeText("El numero secreto es: $numgana")
    //println(numgana)      //muestra el numero ganador

    while (intent != 0) {

        intent --
        aciertos = 0
        aprox = 0
        print("teclea un numero de $longitud cifras: ")

        var numescog = readln()
        if (numescog.length < longitud) {
            while (numescog.length != longitud) {
                numescog = "0" + numescog
            }
        }

        for(i in 0 until longitud) {
            if(numgana[i].toString().toInt() == numescog[i].toString().toInt()) {
                aciertos ++
            } else {
                for(a in 0 until longitud) {
                      if (numescog[i].toString() == numgana[a].toString()) {
                          aprox++
                          break
                      }
                }
            }

        }

        partidag(intentog, numescog.toInt(), aciertos, aprox)
        intentog++

        if(aciertos == longitud) {
            break
        }

        println("Tu numero: $numescog, tinen ${BG_GREEN}${BLACK} $aciertos ${RESET} acierto/s y ${BG_YELLOW}${BLACK} $aprox ${RESET} numero/s que no estan en el lugar correcto")
        println("")

    }

    if(aciertos == longitud) {
        println("Enhorabuena el numero secreto era: $numgana")
        println("Te ha/n sobrado $intent intento/s")
        println("")
    } else {
        println("Vaya no has acertado")
        println("El numero secreto era: $numgana")
        println("")
    }

}

fun partidav() {
    println(File("partidas.txt").readLines())
    println()
}

fun partidag(inten: Int, num: Int, acierto: Int, casi: Int) {
    File("partidas.txt").appendText("| Intento: $inten, Numero: $num, Aciertos: $acierto, Coincidencia: $casi ")
}

