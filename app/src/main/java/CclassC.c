#include <stdio.h> //头文件
#include "com_tencent_nanodetncnn_JavaCallC.h" // java文件头，这里一定要加上上面java语言的头文件

JNIEXPORT void JNICALL Java_com_tencent_nanodetncnn_JavaCallC_varifyCheck  (JNIEnv *env , jobject obj, jchar value) {
// 如果java调用cMethod方法成功，则会打印这句话
printf ( "Java_JavaCallC_cMethod call succ \n" ) ;
}
// 以下所有的内容的内容是测试Cclass.c的语法的，可以省掉。
// 先声明 后调用
void test(){ printf("main C \n");}

//main方法，程序入口,用于测试
int main(){ test();}