import pigpio
import requests
import time

# Imposta il pin di input e l'URL di destinazione
pin = 2
url = 'http://localhost:8080/fancoil/step'

# Variabile per memorizzare l'ultimo timestamp della chiamata REST
last_rest_time = 0

def state_changed(gpio, level, tick):
    global last_rest_time
    if level == 0:
        current_time = time.time()
        # Verifica se è passato abbastanza tempo dall'ultima chiamata REST (100 ms)
        if current_time - last_rest_time >= 0.4:
            print(time.strftime("%Y-%m-%d %H:%M:%S") + " >> Stato basso - invio richiesta REST")
            send_rest_request()
            last_rest_time = current_time

def send_rest_request():
    try:
        response = requests.get(url)
        response.raise_for_status()
        print("Richiesta REST inviata con successo!")
    except requests.exceptions.RequestException as e:
        print(f"Errore nell'invio della richiesta: {e}")

if __name__ == '__main__':
    try:
        pi = pigpio.pi()
        if not pi.connected:
            print("Impossibile connettersi a pigpio. Assicurati che il demone pigpio sia in esecuzione.")
            exit()

        pi.set_mode(pin, pigpio.INPUT)
        pi.set_pull_up_down(pin, pigpio.PUD_UP)
        pi.callback(pin, pigpio.FALLING_EDGE, state_changed)

        print("In attesa di cambiamenti di stato sul pin", pin)
        while True:
            time.sleep(1)
    except KeyboardInterrupt:
        print("Chiusura dello script.")
    finally:
        pi.stop()
