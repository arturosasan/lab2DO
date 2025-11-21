                ##########################################################
                # Segmento de datos
                ##########################################################

                .data 0x10000000
reloj:          .word 0x00010203                # HH:MM:SS (3 bytes de menor peso)

cad_asteriscos: .asciiz "\n  **************************************"
cad_horas:      .asciiz "\n   Horas: "
cad_minutos:    .asciiz " Minutos: "
cad_segundos:   .asciiz " Segundos: "
cad_reloj_en_s: .asciiz "\n   Reloj en segundos: "

                ##########################################################
                # Segmento de código
                ##########################################################

                .globl __start
                .text 0x00400000


__start:        la $a0, reloj

				# EJERCICIO 1
				#sll $v0, $a0, 2			# $v0 = $a0^2 
				#sll $t0, $a0, 5			# $t0 = $a0^5	 
				#addu $v0, $v0, $t0 		# $v0 = $a0*(2^3 + 2^5)
				# FIN EJERCICIO 1
				
				
                # EJERCICIO 2
				# jal inicializa_reloj
				# jal devuelve_reloj_en_s_sd
				# jal imprime_s
				# FIN EJERCICIO 2	

				# EJERCICIO 3
				# jal inicializa_reloj
				# jal devuelve_reloj_en_s_sd
				# jal imprime_s
				# FIN EJERCICIO 3
				
				# EJERCICIO 4.1
				#la $a0, reloj					
				#li $a1, 0x00073015 # Hora 07:48:21
				#jal inicializa_reloj
				#la $a0, reloj
				#jal imprime_reloj
				#la $a0, reloj
				#jal pasa_hora # Incrementa el reloj en una hora
				#la $a0, reloj
				#jal imprime_reloj 
				# FIN EJERCICIO 4.1
				
				# EJERCICIO 4.2
				#la $a0, reloj					
				#li $a1, 0x00173B3B # Hora 
				#jal inicializa_reloj
				#la $a0, reloj
				#jal imprime_reloj
				#la $a0, reloj
				#jal pasa_segundo # Incrementa el reloj en un segundo
				#la $a0, reloj
				#jal imprime_reloj 
				# FIN EJERCICIO 4.2
				
salir:          li $v0, 10              # Código de exit (10)
                syscall                 # Última instrucción ejecutada
				
				
				
				
				
                .end


                ########################################################## 
                # Subrutina que imprime el valor del reloj
                # Entrada: $a0 con la dirección de la variable reloj
                ########################################################## 

imprime_reloj:  move $t0, $a0
                la $a0, cad_asteriscos  # Dirección de la cadena
                li $v0, 4               # Código de print_string
                syscall

                la $a0, cad_horas       # Dirección de la cadena
                li $v0, 4               # Código de print_string
                syscall

                lbu $a0, 2($t0)         # Lee el campo HH
                li $v0, 1               # Código de print_int
                syscall

                la $a0, cad_minutos     # Dirección de la cadena
                li $v0, 4               # Código de print_string
                syscall

                lbu $a0, 1($t0)         # Lee el campo MM
                li $v0, 1               # Código de print_int
                syscall

                la $a0, cad_segundos    # Dirección de la cadena
                li $v0, 4               # Código de print_string
                syscall

                lbu $a0, 0($t0)         # Lee el campo SS
                li $v0, 1               # Código de print_int
                syscall

                la $a0, cad_asteriscos  # Dirección de la cadena
                li $v0, 4               # Código de print_string
                syscall
                jr $ra

                ########################################################## 
                # Subrutina que imprime los segundos calculados
                # Entrada: $a0 con los segundos a imprimir
                ########################################################## 

imprime_s:      move $t0, $a0
                la $a0, cad_asteriscos  # Dirección de la cadena
                li $v0, 4               # Código de print_string
                syscall


                la $a0, cad_reloj_en_s  # Dirección de la cadena
                li $v0, 4               # Código de print_string
                syscall

                move $a0, $t0           # Valor entero a imprimir
                li $v0, 1               # Código de print_int
                syscall

                la $a0, cad_asteriscos  # Dirección de la cadena
                li $v0, 4               # Código de print_string
                syscall
                jr $ra
                
                ########################################################## 
                # Subrutina que incrementa el reloj en una hora
                # Entrada: $a0 con la dirección del reloj
                # Salida: reloj incrementado en memoria
                # Nota: 23:MM:SS -> 00:MM:SS
                ########################################################## 
                
pasa_hora:      lbu $t0, 2($a0)         # $t0 = HH
                addiu $t0, $t0, 1       # $t0 = HH++
                li $t1, 24
                beq $t0, $t1, H24       # Si HH==24 se pone HH a cero
                sb $t0, 2($a0)          # Escribe HH++
                j fin_pasa_hora
H24:            sb $zero, 2($a0)        # Escribe HH a 0
fin_pasa_hora:  jr $ra

inicializa_reloj:
				
				li $t4, 0x001F3F3F		# máscara
				and $a1, $a1, $t4		# máscara 
				
				sw $a1, 0($a0)
				jr $ra

devuelve_reloj_en_s:
				
				lbu $a1, 2($a0)			# horas OK en a1
				li $t1, 3600			
				multu $a1, $t1			# horas * 3600 => segundos
				mflo $a1				 
				addu $v0, $v0, $a1		# valor al registro resultado (v0)

				lbu $a2, 1($a0)			# minutos OK en a2
				li $t2, 60				
				multu $a2, $t2			# minutos * 60 => segundos
				mflo $a2				
				addu $v0, $v0, $a2		# valor al registro resultado (v0)

				lbu $a3, 0($a0)			# segundos OK en $a3
				addu $v0, $v0, $a3		# valor al registro resultado (v0)
		
				jr $ra			
				
devuelve_reloj_en_s_sd:

				lbu $a1, 2($a0)

				sll $v0, $a1, 11		# $v0 = $a0^4 
				sll $t0, $a1, 10		# $t0 = $a0^9	 
				addu $v0, $v0, $a1 		# $v0 = $a0*(2^4 + 2^9)

				sll $t0, $a1, 9			# $v0 = $a0^10
				addu $v0, $v0, $a1 		# $v0 = $a0*(2^4 + 2^9)
				
				sll $t0, $a1, 4			# $v0 = $a0^10
				addu $v0, $v0, $a1 		# $v0 = $a0*(2^4 + 2^9)
				
				addu $v0, $v0, $a1
				
				lbu $a2, 1($a0)
				
				sll $v0, $a2, 5			# $v0 = $a0^4 
				sll $t0, $a2, 4			# $t0 = $a0^9	 
				addu $v0, $v0, $a2 		# $v0 = $a0*(2^4 + 2^9)

				sll $t0, $a2, 3			# $v0 = $a0^10
				addu $v0, $v0, $a2 		# $v0 = $a0*(2^4 + 2^9)
				
				sll $t0, $a2, 2			# $v0 = $a0^10
				addu $v0, $v0, $a2 		# $v0 = $a0*(2^4 + 2^9)
				
				addu $v0, $v0, $a2
				
				lbu $a3, 0($a0)			
				addu $v0, $v0, $a3		
				
				jr $ra
				
pasa_segundo:

				lbu $t0, 0($a0)         # $t0 = SS
                addiu $t0, $t0, 1       # $t0 = SS++
                li $t1, 60
                beq $t0, $t1, S60       # Si SS==60 se pone SS a cero
                sb $t0, 2($a0)          # Escribe SS++
                j fin_pasa_hora
S60:            sb $zero, 0($a0)        # Escribe SS a 0
fin_pasa_segundo:  jr $ra