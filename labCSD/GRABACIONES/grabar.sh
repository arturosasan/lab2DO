#!/usr/bin/env bash

# === CONFIGURACIÓN ===
OUTPREFIX="laboCSD"      # prefijo para el nombre final
TEMPFILE="recording.mkv" # nombre temporal de la grabación
CODEC="libvpx-vp9"       # codec usado en la grabación con wf-recorder

timestamp() {
  date +%Y%m%d%H%M%S #FUNCIÓN PARA AÑOMESDIAHHMMSS
}

if pgrep -x "wf-recorder" >/dev/null; then
  echo "Deteniendo grabación..."
  pkill -INT -x wf-recorder

  sleep 1
  if [[ ! -f "$TEMPFILE" ]]; then
    echo "Error: no se encontró $TEMPFILE"
    exit 1
  fi

  # Nombre final
  FINALNAME="${OUTPREFIX}_$(timestamp).webm"

  # Convertir a WebM y luego eliminar el .mkv original
  echo "Convirtiendo a $FINALNAME..."
  ffmpeg -y -i "$TEMPFILE" -c:v $CODEC -crf 50 -b:v 0 -an "$FINALNAME" &&
    rm -f "$TEMPFILE"

  echo "Listo! El archivo final es: $FINALNAME"
  exit 0
fi

# Si no había grabación en marcha (1er uso)
echo "Iniciando grabación de pantalla..."
wf-recorder -f "$TEMPFILE" -c "$CODEC"
