          .globl __start
          .text 0x00400000
		  
__start:  
Repeat:			  
		  li $a0, 'N' 
		  jal Input
		  move $t0, $v0
		  
		  
		  bltz $t1, CSymbol 
		            	  
OK:						  
		  move $a0, $t1
		  move $a1, $t0
		  jal Mult
          move $t2, $v0
          
		  li $a0, 'R'
		  move $a1, $t2
		  jal Output
		  
		  li $a0, '\n'
		  li $v0, 11
		  syscall
		  
		  bne $t0, $zero, Repeat
		  
		  li $v0, 10
		  syscall
		  
		  
Mult:      
		  li $v0, 0
          beqz $a1, MultRet
		  
MultFor:  
		  add $v0, $v0, $a0
          addi $a1, $a1, -1
          bne $a1, $zero, MultFor
		  
MultRet:  jr $ra

Input:	  
		  li $v0, 11
		  syscall
		  li $a0, '='
		  syscall
		  li $v0, 5
		  syscall
		  jr $ra
		  
Output:	 
		  li $v0, 4
		  syscall
		  li $a0, '='
		  syscall
		  li $v0, 1
		  move $a0, $a1
		  syscall
		  jr $ra
		  
CSymbol : 
		 sub $t0, $zero, $t0
		 sub $t1, $zero, $t1
		 j OK