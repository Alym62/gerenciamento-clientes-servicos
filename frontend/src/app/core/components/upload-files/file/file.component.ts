import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FileService } from 'src/app/core/services/file.service';

@Component({
  selector: 'app-file',
  templateUrl: './file.component.html',
  styleUrls: ['./file.component.scss']
})
export class FileComponent {
  selectedFile: File | null = null;

  constructor(private fileService: FileService, private _snackBar: MatSnackBar) { }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, { duration: 7000 });
  }

  onFileSelected(event: any): void {
    this.selectedFile = event.target.files[0] as File;
  }

  onUpload(): void {
    if (this.selectedFile) {
      this.fileService.uploadFile(this.selectedFile).subscribe(
        response => {
          this.openSnackBar(`✓ ${response}`, 'X')
        }, error => {
          this.openSnackBar(`${error}`, 'X')
        });
    }
  }
}
