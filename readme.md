#โค้ดนี้เป็นโปรแกรม Java ที่ใช้ Weka (Waikato Environment for Knowledge Analysis ในการโหลดโมเดลที่ฝึกไว้ล่วงหน้าและทำการพยากรณ์ค่า PerformanceClass ของ CPU
โดยพิจารณาจากพารามิเตอร์ต่างๆ เช่น MYCT,

\*\*โครงสร้างของโค้ด

1. โหลดโมเดล Weka ที่ผ่านการฝึก จากไฟล์ที่ระบุ
2. รับค่าพารามิเตอร์จาก Command Line
3. สร้างอินสแตนซ์ของข้อมูลที่ตรงกับโมเดลที่โหลด
4. ใช้โมเดล Weka ในการพยากรณ์ PerformanceClass
5. แสดงผลลัพธ์ของการพยากรณ์ออกทางหน้าจอ

\*\*สรุป

1. โหลดโมเดลที่ถูกฝึกไว้จากไฟล์ .model
2. รับค่าพารามิเตอร์ของ CPU จากผู้ใช้
3. สร้าง dataset และเพิ่มอินสแตนซ์ที่ต้องการพยากรณ์ลงไป
4. ใช้โมเดลที่โหลดมาพยากรณ์ค่าของ PerformanceClass
5. แสดงผลลัพธ์ออกทางหน้าจอ

\*\*run server
php -S localhost:8000
