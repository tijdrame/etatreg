import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EtatregSharedModule } from 'app/shared/shared.module';
import { FilesInfosComponent } from './files-infos.component';
import { FilesInfosDetailComponent } from './files-infos-detail.component';
import { FilesInfosUpdateComponent } from './files-infos-update.component';
import { FilesInfosDeleteDialogComponent } from './files-infos-delete-dialog.component';
import { filesInfosRoute } from './files-infos.route';

@NgModule({
  imports: [EtatregSharedModule, RouterModule.forChild(filesInfosRoute)],
  declarations: [FilesInfosComponent, FilesInfosDetailComponent, FilesInfosUpdateComponent, FilesInfosDeleteDialogComponent],
  entryComponents: [FilesInfosDeleteDialogComponent],
})
export class EtatregFilesInfosModule {}
