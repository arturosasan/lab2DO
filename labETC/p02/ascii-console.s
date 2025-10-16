	.globl __start
	.text 0x00400000

__start:
		li $s0,32
		li $s1,127
		li $s2,4
loop:
		addi $s2,-1
		li $v0,1
		move $a0,$s0
		syscall
		li $v0,11
		li $a0,9
		syscall
		li $v0,11
		move $a0,$s0
		syscall
bucle: 	
		bgtz $s2, else
		li $s2,4
		li $v0,11
		li $a0, '\n'
		syscall
		j finally
else: 	
		li $v0,11
		li $a0, '\t'
		syscall
finally: 	
		addi $s0,$s0,1
		blt $s0,$s1,loop
		li $v0,10
		syscall