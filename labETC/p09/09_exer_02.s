            .globl __start
            .data 0x10000000
demana:     .asciiz "Escriba alguna cosa: "
contesta:   .asciiz "Ha escrito: "
contador:   .asciiz "La longitud es: "
cadena:     .space 80
            .text 0x00400000

__start:    la $a0, demana     # carga la dirección de la frase introductoria
            la $a1, cadena     # carga la dirección de la respuesta
            li $a2, 80
            la $a3, contesta   # carga la dirección de la frase respuesta

            jal InputS

	    la $a0, contesta
	    jal OutputS

	    la $a0, cadena	#Cargo el puntero a cadena
	    jal StrLength		#Llamada al método StrLength

            li $v0,10
            syscall

InputS:     li $v0, 4
            syscall
            li $v0, 8 		# guardar en el espacio determinado la cadena introducida
            move $a0, $a1	# el registro a0 contiene la dirección de la cadena
            move $a1, $a2	# el registro a1 contiene lalongitud máxima de la cadena
            syscall
	    jr $ra
	
            
OutputS:    li $v0, 4
            syscall 		# imprime la frase de respuesta
            la $a0, cadena
            li $v0, 4
            syscall 		# imprime la frase guardada en cadena
            jr $ra

#####################################
########### EJERCICIO 3 #############
#####################################

StrLength:  la $t0, cadena		
            li $t1, 10			# FIN DE LINEA
		  
bucle:  lb $t2, 0($t0)		
	beq $t2, $t1, fin		# Si el carácter actual es LF o el NULL, se termina
	beq $t2, $0, fin		# En caso de que sea NULL el carácter que se lee es porque se ha excedido el maximo de 80 caracteres							
	addi $t0, $t0, 1		# Añade 1 al regitro $t0.
	j bucle
		  
fin:	sub $a2, $t0, $a0		# Aquí utiizamos el contador en $a0 para calcular la
	la $a0, contador		# resta entre el final y el principio para saber cuantos caracteres hay
	li $v0, 4
	syscall
		  
	move $a0, $a2
	li $v0, 1
	syscall	  
	li $v0, 11
	li $a0, 10
	syscall		  
	jr $ra
		  