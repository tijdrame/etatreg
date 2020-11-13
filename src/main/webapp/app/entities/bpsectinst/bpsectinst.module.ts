import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EtatregSharedModule } from 'app/shared/shared.module';
import { BpsectinstComponent } from './bpsectinst.component';
import { BpsectinstDetailComponent } from './bpsectinst-detail.component';
import { BpsectinstUpdateComponent } from './bpsectinst-update.component';
import { BpsectinstDeleteDialogComponent } from './bpsectinst-delete-dialog.component';
import { bpsectinstRoute } from './bpsectinst.route';

@NgModule({
  imports: [EtatregSharedModule, RouterModule.forChild(bpsectinstRoute)],
  declarations: [BpsectinstComponent, BpsectinstDetailComponent, BpsectinstUpdateComponent, BpsectinstDeleteDialogComponent],
  entryComponents: [BpsectinstDeleteDialogComponent],
})
export class EtatregBpsectinstModule {}
