.386
.model flat, stdcall
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;includem biblioteci, si declaram ce functii vrem sa importam
includelib msvcrt.lib
extern exit: proc
extern malloc: proc
extern memset: proc

includelib canvas.lib
extern BeginDrawing: proc
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;declaram simbolul start ca public - de acolo incepe executia
public start
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;sectiunile programului, date, respectiv cod
.data
;aici declaram date
window_title DB "Proiect asamblare",0
area_width EQU 960
area_height EQU 576
area DD 0

counter DD 0 ; numara evenimentele de tip timer

x dd 0
arg1 EQU 8
arg2 EQU 12
arg3 EQU 16
arg4 EQU 20

symbol_width EQU 10
symbol_height EQU 20
include digits.inc
include letters.inc
include noua_mea_racheta.inc
include red_heart.inc
include grey_heart.inc
include patrat.inc
include directii.inc
include extraterestru.inc
include piupiupiu.inc

piu_x dd 0
piu_width EQU 10
piu_height EQU 20
extraterestru_x dW 330
extraterestru_x dW 330
extraterestru_x1 dd 380
extraterestru_x2 dd 430
extraterestru_x3 dd 480
extraterestru_x4 dd 530
extraterestru_x5 dd 580
extraterestru_y dd 235
extraterestru_y1 dd 285
extraterestru_y2 dd 335
heart_width equ 30
heart_height equ 30
heart_size equ 30
heartg_width equ 30
heartg_height equ 30
heartg_size equ 30
y dd 0
rocket_width equ 48
rocket_height equ 48
extraterestru_width equ 48
extraterestru_height equ 48
extraterestru_size equ 48
patrat_width equ 48
patrat_height equ 48
rocket_size equ 48
rocket_x dd 480
rocket_y dd 488
patrat_size EQU 48
patrat_x equ 48
patrat_y equ 48
count dd 0
ceva dd 0
piupiu_x dd 500
piupiu_y dd 460
score dd 0
placebo dd 3

;directii dd 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0

racheta_mov dd 4

poz_extraterestru_x dd 481
poz_extraterestru_y dd 151

matrice dd 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
		dd 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
		dd 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0
		dd 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0
        dd 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0
		dd 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0
        dd 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
        dd 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
        dd 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
        dd 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
        dd 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
        dd 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
        dd 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
					  



.code
; procedura make_text afiseaza o litera sau o cifra la coordonatele date
; 8 - simbolul de afisat (litera sau cifra)
; 12 - pointer la vectorul de pixeli
; 16 - pos_x
; 20 - pos_y



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;   LITERE
 
make_text proc
	push ebp
	mov ebp, esp
	pusha
	
	mov eax, [ebp+8] ; citim simbolul de afisat
	cmp eax, 'A'
	jl make_digit
	cmp eax, 'Z'
	jg make_digit
	sub eax, 'A'
	lea esi, letters
	jmp draw_text
make_digit:
	cmp eax, '0'
	jl make_space
	cmp eax, '9'
	jg make_space
	sub eax, '0'
	lea esi, digits
	jmp draw_text
make_space:	
	mov eax, 26 ; de la 0 pana la 25 sunt litere, 26 e space
	lea esi, letters
	
draw_text:
	mov ebx, symbol_width
	mul ebx
	mov ebx, symbol_height
	mul ebx
	add esi, eax
	mov ecx, symbol_height
bucla_simbol_linii:
	mov edi, [ebp+12] ; pointer la matricea de pixeli
	mov eax, [ebp+20] ; pointer la coord y
	add eax, symbol_height
	sub eax, ecx
	mov ebx, area_width
	mul ebx
	add eax, [ebp+16] ; pointer la coord x
	shl eax, 2 ; inmultim cu 4, avem un DWORD per pixel
	add edi, eax
	push ecx
	mov ecx, symbol_width
bucla_simbol_coloane:
	cmp byte ptr [esi], 0
	je simbol_pixel_alb
	mov dword ptr [edi], 0F5F5F5h
	jmp simbol_pixel_next
simbol_pixel_alb:
	mov dword ptr [edi],01C1C1Ch
simbol_pixel_next:
	inc esi
	add edi, 4
	loop bucla_simbol_coloane
	pop ecx
	loop bucla_simbol_linii
	popa
	
	mov esp, ebp
	pop ebp
	ret
make_text endp

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; VIETI CU ROSU

make_heart proc
	push ebp
	mov ebp, esp
	pusha
	cmp eax, 'a'
	mov eax, [ebp+8]
	sub eax,'a'
	lea esi, red_heart
	jmp draw_heart
	
draw_heart:
	mov ebx, heart_width
	mul ebx
	mov ebx, heart_height
	mul ebx
	add esi, eax
	mov ecx,heart_height
bucla_simbol_linii1:
	mov edi, [ebp+12] ; pointer la matricea de pixeli
	mov eax, [ebp+20] ; pointer la coord y
	add eax, heart_height
	sub eax, ecx
	mov ebx, area_width
	mul ebx
	add eax, [ebp+16] ; pointer la coord x
	shl eax, 2 ; inmultim cu 4, avem un DWORD per pixel
	add edi, eax
	push ecx
	mov ecx, heart_width
bucla_simbol_coloane1:
	cmp byte ptr [esi], 0
	je simbol_pixel_alb1
	mov dword ptr [edi], 0f05050h
	jmp simbol_pixel_next1
simbol_pixel_alb1:
	mov dword ptr [edi],01C1C1Ch
simbol_pixel_next1:
	inc esi
	add edi, 4
	loop bucla_simbol_coloane1
	pop ecx
	loop bucla_simbol_linii1
	popa
	
	mov esp, ebp
	pop ebp
	ret

make_heart endp



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; EXTRATERESTRU

make_extraterestru proc
	
	xor esi,esi
	xor edi,edi
	mov edi,0
	mov ecx,0
	mov ebx,0
	push ebp
	mov ebp, esp
	pusha
	cmp eax, 'f'
	mov eax, [ebp+8]
	sub eax,'f'
	lea esi, extraterestru
	jmp draw_extraterestru
	


draw_extraterestru:
	mov ebx, extraterestru_width
	mul ebx
	mov ebx, extraterestru_height
	mul ebx
	add esi, eax
	mov ecx,extraterestru_height
bucla_simbol_linii11v:
	mov edi, [ebp+12] ; pointer la matricea de pixeli
	mov eax, [ebp+20] ; pointer la coord y
	add eax, extraterestru_height
	sub eax, ecx
	mov ebx, area_width
	mul ebx
	add eax, [ebp+16] ; pointer la coord x
	shl eax, 2 ; inmultim cu 4, avem un DWORD per pixel
	add edi, eax
	push ecx
	mov ecx, extraterestru_width
	
bucla_simbol_coloane11v:
	cmp byte ptr [esi], 0
	je simbol_pixel_alb11v
	mov dword ptr [edi], 08ce6ffh
	jmp simbol_pixel_next11v
simbol_pixel_alb11v:
mov dword ptr [edi], 0bcbcbch
	mov dword ptr [edi],01C1C1Ch
simbol_pixel_next11v:
	inc esi
	add edi, 4
	loop bucla_simbol_coloane11v
	pop ecx
	;call miscare_tastatura
	loop bucla_simbol_linii11v
	;call miscare_tastatura
	popa
	
	mov esp, ebp
	pop ebp
	ret

make_extraterestru endp

extraterestru_macro macro symbol1, x,y,drawarea

	push y
	push x
	
	push drawarea
	push symbol1
	call make_extraterestru
	add esp, 16

endm

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; MAI MULTI EXTRATERESTRII

make_mai_multi_extraterestrii proc
mov ecx,poz_extraterestru_x
 	push ebp
	mov ebp, esp
	pusha
	cmp eax, 'p'
	mov eax, [ebp+8]
	sub eax,'p'
	mov esi,0
verificare1:	
	cmp matrice[esi][ebp],1
	je deseneaza
	
	
deseneaza:
extraterestru_macro 'f',poz_extraterestru_x,poz_extraterestru_y,area
add poz_extraterestru_x,48
cmp esi,22
je reset

reset: 
add poz_extraterestru_y,48
mov poz_extraterestru_x,ecx
cmp esi,154
jne verificare1
	
	


make_mai_multi_extraterestrii endp

make_mai_multi_extraterestrii_macro macro symboll, drawarea
push drawarea
push symboll
call make_mai_multi_extraterestrii
add esp,4


endm


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; PATRAT


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; VIETI CU GRI


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; RACHETA

make_rocket proc

	xor esi,esi
	xor edi,edi
	mov edi,0
	mov ecx,0
	mov ebx,0
	push ebp
	mov ebp, esp
	pusha
	cmp eax, 'b'
	mov eax, [ebp+8]
	sub eax,'b'
	lea esi, noua_mea_racheta
	jmp draw_rocket
	


draw_rocket:
	mov ebx, rocket_width
	mul ebx
	mov ebx, rocket_height
	mul ebx
	add esi, eax
	mov ecx,rocket_height
bucla_simbol_linii11:
	mov edi, [ebp+12] ; pointer la matricea de pixeli
	mov eax, [ebp+20] ; pointer la coord y
	add eax, rocket_height
	sub eax, ecx
	mov ebx, area_width
	mul ebx
	add eax, [ebp+16] ; pointer la coord x
	shl eax, 2 ; inmultim cu 4, avem un DWORD per pixel
	add edi, eax
	push ecx
	mov ecx, rocket_width
	
bucla_simbol_coloane11:
	cmp byte ptr [esi], 0
	je simbol_pixel_alb11
	mov dword ptr [edi], 06aa84fh
	jmp simbol_pixel_next11
simbol_pixel_alb11:
mov dword ptr [edi], 0bcbcbch
	mov dword ptr [edi],01C1C1Ch
simbol_pixel_next11:
	inc esi
	add edi, 4
	loop bucla_simbol_coloane11
	pop ecx
	;call miscare_tastatura
	loop bucla_simbol_linii11
	;call miscare_tastatura
	popa
	
	mov esp, ebp
	pop ebp
	ret

make_rocket endp

rocket_macro macro x,y,symbol1,drawarea
	push y
	push x
	push drawarea
	push symbol1
	call make_rocket
	add esp, 16

endm

pune_racheta_pe_ecran proc
reincepere:
		cmp eax, 'e'
	mov eax, [ebp+8]
	sub eax,'e'
	mov ebx,0
	mov ecx,11
	mov eax,20
	mul ecx
	sal eax, 2
	mov ecx, eax
reincepere2:
	;cmp matrice1[ebx][ecx],1
	je deseneaza_racheta
	jne did_you_forget
deseneaza_racheta:
;mov edi,10
rocket_macro ebx,ecx,'b',area

did_you_forget:
	add ebx,4
	cmp ebx,19*4
	je reincepere
	jmp reincepere2


pune_racheta_pe_ecran endp

pune_racheta_pe_ecran_macro macro simbol,draw_area

	push simbol
	push draw_area
	call pune_racheta_pe_ecran
	add esi,4

endm
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; PIUPIU

misiunea_piupiu proc
xor esi,esi
	xor edi,edi
	mov edi,0
	mov ecx,0
	mov ebx,0
	push ebp
	mov ebp, esp
	pusha
	cmp eax, 'i'
	mov eax, [ebp+8]
	sub eax,'i'
	lea esi, piupiupiu
	jmp draw_piu

	draw_piu:
	mov ebx, piu_width
	mul ebx
	mov ebx, piu_height
	mul ebx
	add esi, eax
	mov ecx,piu_height
bucla_simbol_linii_piu:
	mov edi, [ebp+12] ; pointer la matricea de pixeli
	mov eax, [ebp+20] ; pointer la coord y
	add eax, rocket_height
	sub eax, ecx
	mov ebx, area_width
	mul ebx
	add eax, [ebp+16] ; pointer la coord x
	shl eax, 2 ; inmultim cu 4, avem un DWORD per pixel
	add edi, eax
	push ecx
	mov ecx, piu_width
	
bucla_simbol_coloane_piu:
	cmp byte ptr [esi], 0
	je simbol_pixel_alb_piu
	mov dword ptr [edi], 0cc0000h
	jmp simbol_pixel_next_piu
simbol_pixel_alb_piu:
mov dword ptr [edi], 0bcbcbch
	mov dword ptr [edi],01C1C1Ch
simbol_pixel_next_piu:
	inc esi
	add edi, 4
	loop bucla_simbol_coloane_piu
	pop ecx
	;call miscare_tastatura
	loop bucla_simbol_linii_piu
	;call miscare_tastatura
	popa
	
	mov esp, ebp
	pop ebp
	ret

misiunea_piupiu endp

piupiu_macro macro symbol1, x,y,drawarea

	push y
	push x
	
	push drawarea
	push symbol1
	call misiunea_piupiu
	add esp, 16

endm


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; MISCARE 

; miscare_tastatura proc near

	; push ebp
	; mov ebp, esp
	; pusha
	; cmp eax, 't'
	; mov eax, [ebp+8]
	; sub eax,'t'
	; lea esi, directii
	; jmp verificare

; verificare:
	 ; mov eax, 1
	 ; push ax
	 ; int 16h

	; mov ah,00h
	; int 16h


	; cmp al,41h
	; je miscare_la_stanga
	; cmp al,61h
	; je miscare_la_stanga

	; cmp al,44h
	; je miscare_la_dreapta
	; cmp al,64h
	; je miscare_la_dreapta

	
; miscare_la_stanga:
; mov ecx,rocket_x
; add ecx,4
; mov rocket_x,ecx
   
	
	
; miscare_la_dreapta:
	; mov ecx,rocket_x
	; sub ecx,4
	; mov rocket_x,ecx

	
	
	
; miscare_tastatura endp
	



; un macro ca sa apelam mai usor desenarea simbolului
scrieare_text macro symbol, drawArea, x, y
	push y
	push x
	push drawArea
	push symbol
	call make_text
	add esp, 16
endm

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; LINII
line_horizontal macro x, y, len, color
local bucla_line
	mov eax, y
	mov ebx, area_width
	mul ebx 
	add eax, x 
	shl eax, 2 
	add eax, area
	mov ecx, len
	bucla_line:
	mov dword ptr [eax], color
	add eax, 4
	loop bucla_line
endm

line_vertical macro x, y, len, color
local bucla_line
	mov eax, y
	mov ebx, area_width
	mul ebx 
	add eax, x 
	shl eax, 2 
	add eax, area
	mov ecx, len
	bucla_line:
	mov dword ptr [eax], color
	add eax, area_width*4
	loop bucla_line
endm

vieti_macro macro symbol1, x,y,drawarea

	push y
	push x
	
	push drawarea
	push symbol1
	call make_heart
	add esp, 16

endm

; functia de desenare - se apeleaza la fiecare click
; sau la fiecare interval de 200ms in care nu s-a dat click
; 8 - evt (0 - initializare, 1 - click, 2 - s-a scurs intervalul fara click, 3 - s-a apasat o tasta)
; 12 - x (in cazul apasarii unei taste, x contine codul ascii al tastei care a fost apasata)
; 16 - y


draw proc
	push ebp
	mov ebp, esp
	pusha
	mov eax,[ebp+arg2]
	cmp eax,0000044h
	je miscare_la_dreapta
	cmp eax,0000064h
	je miscare_la_dreapta

	cmp eax,0000041h
	je miscare_la_stanga
	cmp eax,0000061h
	je miscare_la_stanga
	
	;mov ecx,100
	cmp eax,0000020h
	mov y,1
	;mov x,1	
	je miscare_piupiu

	mov eax, [ebp+arg1]
	cmp eax, 1
	jz evt_click
	cmp eax, 2
	jz evt_timer ; nu s-a efectuat click pe nimic
	;mai jos e codul care intializeaza fereastra cu pixeli albi
	mov eax, area_width
	mov ebx, area_height
	mul ebx
	shl eax, 2
	push eax
	push 01C1C1Ch
	push area
	call memset
	add esp, 12
	jmp afisare_litere
	
	

	; cmp eax,0000057h 
	; je miscare_in_sus
	; cmp eax,0000077h
	; je miscare_in_sus

	
	
evt_click:
line_horizontal [ebp+12], [ebp+16], 30, 0FFh
	jmp afisare_litere
; mov eax,0
; mov edx,0
evt_timer:
	inc counter
	inc x
	inc ceva
	
	cmp counter,810
	je sfarsit

	;jmp afisare_litere

vrif:
	cmp x,30
	jb adaug
	ja scad
	
; vrif2:
	; cmp x,60
	; ja adaug2
	; jb scad
	
	;jb adaug
; cmp x,60
; ja adaug
; ja scad



adaug:
	add extraterestru_x,2
	add extraterestru_x1,2
	add extraterestru_x2,2
	add extraterestru_x3,2
	add extraterestru_x4,2
	add extraterestru_x5,2
		mov eax,x
	mov edx,0
	mov ebx,30
	div ebx
	cmp edx,0
	je vezi2
	jmp afisare_litere 
	vezi2:
    add extraterestru_y,4
	add extraterestru_y1,4
	add extraterestru_y2,4
mov edx,2
	;jmp scad
	; cmp x,30
	; je

	jmp afisare_litere
scad:
	sub extraterestru_x,2
	sub extraterestru_x1,2
	sub extraterestru_x2,2
	sub extraterestru_x3,2
	sub extraterestru_x4,2
	sub extraterestru_x5,2
	
	mov eax,x
	mov edx,0
	mov ebx,30
	div ebx
	cmp edx,0
	je vezi
	jmp afisare_litere 
	vezi:
    add extraterestru_y,4
	add extraterestru_y1,4
	add extraterestru_y2,4
	mov x,0
	mov edx,2
	jmp afisare_litere
	;jmp scad

	; cmp extraterestru_x,330
; je plus_y2



; plus_y2:
	;add extraterestru_y,2
	; cmp extraterestru_y1,193
	; je adaug







miscare_la_stanga:
	sub rocket_x,1
	mov eax,rocket_x
	add eax,20
	mov piupiu_x,eax
	jmp afisare_litere
miscare_la_dreapta:	
	add rocket_x,1
		mov eax,rocket_x
	add eax,20
	mov piupiu_x,eax
jmp afisare_litere


miscare_piupiu:

cmp y,1

je misca
;jne stai

jmp afisare_litere
misca:
piupiu_macro 'i', piupiu_x,piupiu_y,area
cmp ceva,20
jb sus
je stai 
sus:       
sub piupiu_y,2

;e stai
jmp afisare_litere
stai:

mov piupiu_y,460
mov ceva,0
jmp afisare_litere

	;jmp afisare_litere

; jmp afisare_litere

;muta:
;mov

; muta_linia_vietii:

; vezi_miscare:
; mov ebx,counter
; cmp ebx,x
; add extraterestru_x,4
; add x,2
sfarsit:
	scrieare_text 'G', area, 300,200
	scrieare_text 'A', area, 320,200
	scrieare_text 'M', area, 340,200
	scrieare_text 'E', area, 360,200
	scrieare_text 'O', area, 380,200
	scrieare_text 'V', area, 400,200
	scrieare_text 'E', area, 420,200
	scrieare_text 'R', area, 440,200
 jmp afisare_litere
	
	 
	
afisare_litere:

	;afisam valoarea counter-ului curent (sute, zeci si unitati)
	mov ebx, 10
	mov eax, counter
	;cifra unitatilor
	mov edx, 0
	div ebx
	add edx, '0'
	scrieare_text edx, area, 30, 10
	;cifra zecilor
	mov edx, 0
	div ebx
	add edx, '0'
	scrieare_text edx, area, 20, 10
	;cifra sutelor
	mov edx, 0
	div ebx
	add edx, '0'
	scrieare_text edx, area, 10, 10
	
	
	
	
	extraterestru_macro 'f',extraterestru_x,extraterestru_y,area
	extraterestru_macro 'f',extraterestru_x3,extraterestru_y,area
	extraterestru_macro 'f',extraterestru_x1,extraterestru_y,area
	extraterestru_macro 'f',extraterestru_x2,extraterestru_y,area
	extraterestru_macro 'f',extraterestru_x4,extraterestru_y,area
	extraterestru_macro 'f',extraterestru_x5,extraterestru_y,area

	
	extraterestru_macro 'f',extraterestru_x,extraterestru_y1,area
	extraterestru_macro 'f',extraterestru_x3,extraterestru_y1,area
	extraterestru_macro 'f',extraterestru_x1,extraterestru_y1,area
	extraterestru_macro 'f',extraterestru_x2,extraterestru_y1,area
	extraterestru_macro 'f',extraterestru_x4,extraterestru_y1,area
	extraterestru_macro 'f',extraterestru_x5,extraterestru_y1,area
	
	extraterestru_macro 'f',extraterestru_x,extraterestru_y2,area
	extraterestru_macro 'f',extraterestru_x3,extraterestru_y2,area
	extraterestru_macro 'f',extraterestru_x1,extraterestru_y2,area
	extraterestru_macro 'f',extraterestru_x2,extraterestru_y2,area
	extraterestru_macro 'f',extraterestru_x4,extraterestru_y2,area
	extraterestru_macro 'f',extraterestru_x5,extraterestru_y2,area
	
	
	;scriem un mesaj
	scrieare_text 'S', area, 70, 7
	scrieare_text 'C', area, 80, 7
	scrieare_text 'O', area, 90, 7
	scrieare_text 'R', area, 100, 7
	
	mov ebx, 10
	mov eax, score
	;cifra unitatilor
	mov edx, 0
	div ebx
	add edx, '0'
	scrieare_text edx, area, 130, 7
	;cifra zecilor
	mov edx, 0
	div ebx
	add edx, '0'
	scrieare_text edx, area, 120, 7
	; scrieare_text 'E', area, 110, 7
	; scrieare_text 'C', area, 120, 7
	; scrieare_text 'T', area, 130, 7
	
	; scrieare_text 'L', area, 90, 27
	; scrieare_text 'A', area, 100, 27
	
	; scrieare_text 'A', area, 60, 47
	; scrieare_text 'S', area, 70, 47
	; scrieare_text 'A', area, 80, 47
	; scrieare_text 'M', area, 90, 47
	; scrieare_text 'B', area, 100, 47
	; scrieare_text 'L', area, 110, 47
	; scrieare_text 'A', area, 120, 47
	; scrieare_text 'R', area, 130, 47
	; scrieare_text 'E', area, 140, 47
	
	
	; scrieare_text 'C', area, 1089, 7
	; scrieare_text 'A', area, 1099, 7
	; scrieare_text 'P', area, 1109, 7
	; scrieare_text 'R', area, 1119, 7
	; scrieare_text 'I', area, 1129, 7
	; scrieare_text 'T', area, 1139, 7
	; scrieare_text 'A', area, 1149, 7
	; scrieare_text 'A', area, 1169, 7
	; scrieare_text 'N', area, 1179, 7
	; scrieare_text 'D', area, 1189, 7
	; scrieare_text 'R', area, 1199, 7
	; scrieare_text 'E', area, 1209, 7
	; scrieare_text 'E', area, 1219, 7
	; scrieare_text 'A', area, 1229, 7
	vieti_macro 'a',850,61,area
	vieti_macro 'a',890,61,area
	vieti_macro 'a',930,61,area

	;mov piu_x,piupiu_x

	rocket_macro rocket_x,rocket_y,'b',area
	;rocket_macro 'b',560,560,area
	;make_mai_multi_extraterestrii_macro 'p',area
	;extraterestru_macro 'f',266,266,area
	;miscare_macro 't',area
	;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; S
	; loot:
	; cmp count,5
	; jmp drawn1
	; drawn1:
	; patrat_macro 'c',310,39,area
	; inc count
	; cmp count, 10000
	; jne loot
	; patrat_macro 'c',270,39,area

; cmp eax,15
; jmp draw3
; draw3:
	; patrat_macro 'c',230,39,area
	; cmp counter,20
; jmp draw4
; draw4:
	;patrat_macro 'c',190,109,area
	 ; patrat_macro 'c',190,69,area
	; patrat_macro 'c',230,139,area
	; patrat_macro 'c',270,159,area
	; patrat_macro 'c',310,179,area
	; patrat_macro 'c',310,219,area
	; patrat_macro 'c',270,239,area
	; patrat_macro 'c',230,239,area
	; patrat_macro 'c',190,239,area
	
	;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; P
	; patrat_macro 'c',380,39,area
	; patrat_macro 'c',380,79,area
	; patrat_macro 'c',380,119,area
	; patrat_macro 'c',380,159,area
	; patrat_macro 'c',380,199,area
	; patrat_macro 'c',380,239,area
	; patrat_macro 'c',420,39,area
	; patrat_macro 'c',460,59,area
	; patrat_macro 'c',460,99,area
	; patrat_macro 'c',420,119,area
	

	;vieti_gri_macro 'q',1120,100,area
	; make_image_macro 'r',area, 50, 50
	 
	; line_horizontal patrat_x,patrat_y,patrat_size,0F5F5F5h
	; line_horizontal patrat_x,patrat_y+patrat_size,patrat_size,0F5F5F5h
	; line_vertical patrat_x,patrat_y,patrat_size,0F5F5F5h
 ; line_vertical patrat_x+patrat_size,patrat_y,patrat_size,0F5F5F5h
	
	;racheta '1',area, 50, 50

final_draw:
	popa
	mov esp, ebp
	pop ebp
	ret
draw endp

start:
	;alocam memorie pentru zona de desenat
	mov eax, area_width
	mov ebx, area_height
	mul ebx
	shl eax, 2
	push eax
	call malloc
	add esp, 4
	mov area, eax
	
	
	
	;call miscare_tastatura
	;apelam functia de desenare a ferestrei
	; typedef void (*DrawFunc)(int evt, int x, int y);
	; void __cdecl BeginDrawing(const char *title, int width, int height, unsigned int *area, DrawFunc draw);
	push offset draw
	push area
	push area_height
	push area_width
	push offset window_title
	call BeginDrawing
	add esp, 20

	;terminarea programului
	push 0
	call exit
end start
