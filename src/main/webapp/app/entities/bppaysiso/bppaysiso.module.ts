import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EtatregSharedModule } from 'app/shared/shared.module';
import { BppaysisoComponent } from './bppaysiso.component';
import { BppaysisoDetailComponent } from './bppaysiso-detail.component';
import { BppaysisoUpdateComponent } from './bppaysiso-update.component';
import { BppaysisoDeleteDialogComponent } from './bppaysiso-delete-dialog.component';
import { bppaysisoRoute } from './bppaysiso.route';

@NgModule({
  imports: [EtatregSharedModule, RouterModule.forChild(bppaysisoRoute)],
  declarations: [BppaysisoComponent, BppaysisoDetailComponent, BppaysisoUpdateComponent, BppaysisoDeleteDialogComponent],
  entryComponents: [BppaysisoDeleteDialogComponent],
})
export class EtatregBppaysisoModule {}
