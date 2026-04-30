#!/bin/bash

MIGRATIONS_DIR="db/migration"

read -p "Digite o nome da migration: " NAME

SAFE_NAME=$(echo "$NAME" | tr ' ' '_' | tr -cd '[:alnum:]_')

TIMESTAMP=$(date +"%Y%m%d%H%M%S")

FILE_NAME="V${TIMESTAMP}__${SAFE_NAME}.sql"

FILE_PATH="${MIGRATIONS_DIR}/${FILE_NAME}"

touch "$FILE_PATH"