.data 0x10001008
A:      .word   2000
B:      .byte   190
C:      .byte   210
Delta: .word   12000

.text
.globl __start

__start:
la $t0,  0x10001010
lw $t1, 0($t0)
