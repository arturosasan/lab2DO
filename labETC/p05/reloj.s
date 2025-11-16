                ##########################################################
                # Segmento de datos
                ##########################################################

                .data 0x10000000
reloj:          .word 0                # HH:MM:SS (3 bytes de menor peso)

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

__start:        
				la $a0, reloj
				jal imprime_reloj
				
				#la $a0, reloj					##########################################################
				#li $a1, 0x0002030C				##########################################################
				#jal inicializa_reloj			######					EJERCICIO 1					######
				#la $a0, reloj					##########################################################
				#jal imprime_reloj				##########################################################
				
				#la $a0, reloj					########################################################## 
				#li $a1, 0x0012202D				##########################################################
				#jal inicializa_reloj			##########################################################
				#la $a0, reloj					#######					EJERCICIO 2					######
				#jal devuelve_reloj_en_s		##########################################################
				#move $a0, $v0					##########################################################
				#jal imprime_s					##########################################################
				
				#la $a0, reloj					##########################################################
				#li $a1, 66765					##########################################################
				#jal inicializa_reloj_en_s		#######					EJERCICIO 3					######
				#la $a0, reloj					##########################################################
				#jal imprime_reloj				##########################################################
					
				
				
				
				#li $a1, 2						# cargar horas en a1
				#li $a2, 3						# cargar minutos en a2
				#li $a3, 12						# cargar segundos en a3
				#jal inicializa_reloj_alt		# llamar función
				#jal imprime_reloj

				#li $a1, 2
				#jal inicializa_reloj_hh

				#li $a1, 3
				#jal inicializa_reloj_mm

				#li $a1, 12
				#jal inicializa_reloj_ss
				
				#jal imprime_reloj
				
				
             
salir:          li $v0, 10              # Código de exit (10)
                syscall                 # Última instrucción ejecutada
                .end


                ########################################################## 
                # Subrutina que imprime el valor del reloj
                # Entrada: $a0 con la dirección de la variable reloj
                ########################################################## 

inicializa_reloj:
				
				li $t4, 0x001F3F3F		# máscara
				and $a1, $a1, $t4		# máscara 
				
				sw $a1, 0($a0)
				jr $ra
				
inicializa_reloj_alt:

				sll $t1, $a1, 16		# horas OK en t1
				sll $t2, $a2, 8			# minutos OK en t2
				or $t0, $t1, $t2		# junta horas y mins en t0
				or $t0, $t0, $a3		# añade las horas a t0
				
				li $t4, 0x001F3F3F		# máscara
				and $t0, $t0, $t4		# máscara 
				
				sw $t0, 0($a0)
				jr $ra

inicializa_reloj_hh:
				
				sll $t1, $a1, 16				# horas OK en t1
		
				li $t4, 0x001F3F3F
				and $t1, $t1, $t4
		
				sb $t1, 0($a0)
				jr $ra

inicializa_reloj_mm:
				
				sll $t1, $a1, 8					# minutos OK en t1

				li $t4, 0x001F3F3F
				and $t1, $t1, $t4

				sb $t1, 0($a0)
				jr $ra

inicializa_reloj_ss:
				
				sb $a1, 0($a0)

				li $t4, 0x001F3F3F
				and $a1, $a1, $t4

				jr $ra

inicializa_reloj_en_s:

        li $t1, 60
        divu $a1, $t0
        mfhi $t1
        sb $t1, 0($a0)

        mflo $t1
        divu $t1, $t0
        mfhi $t1
        sb $t1, 1($a0)

        mflo $t1
        sb $t1,2($a0)
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

imprime_reloj:  

				move $t0, $a0
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

imprime_s:      
				
				move $t0, $a0
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
