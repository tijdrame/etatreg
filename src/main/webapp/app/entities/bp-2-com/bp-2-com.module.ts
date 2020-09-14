import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EtatregSharedModule } from 'app/shared/shared.module';
import { Bp2ComComponent } from './bp-2-com.component';
import { Bp2ComDetailComponent } from './bp-2-com-detail.component';
import { Bp2ComUpdateComponent } from './bp-2-com-update.component';
import { Bp2ComDeleteDialogComponent } from './bp-2-com-delete-dialog.component';
import { bp2ComRoute } from './bp-2-com.route';

@NgModule({
  imports: [EtatregSharedModule, RouterModule.forChild(bp2ComRoute)],
  declarations: [Bp2ComComponent, Bp2ComDetailComponent, Bp2ComUpdateComponent, Bp2ComDeleteDialogComponent],
  entryComponents: [Bp2ComDeleteDialogComponent],
})
export class EtatregBp2ComModule {}
